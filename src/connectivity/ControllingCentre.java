package connectivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Stack;
import converters.Bit;
import converters.Converter;
import converters.Octet;
import exceptions.StdException;
import iec_60870_5_104.apdu.APDU;
import iec_60870_5_104.apdu.CFType;

/**
 * this is a template class to be extended by other Java classes desiring to be
 * rendered as applications of the 104 standard
 * 
 * @author ar421
 *
 */
public class ControllingCentre {
	protected Stack<APDU> sendList, receiveList;
	public Stack<APDU> getSendList() {
		return sendList;
	}

	public void setSendList(Stack<APDU> sendList) {
		this.sendList = sendList;
	}

	private APDU tempApdu;
	protected FSMState context;
	protected Socket connection;
	private boolean form;
	protected int Vs, Vr, Ack, k, w, timeout, t1, t2, t3, port;
	private byte[] ip;
	private String[] str;
	private InetAddress IP;
	protected boolean overflow;
	protected boolean undisturbed;

	/**
	 * a main method to be called by the user to connect to a server. commands:
	 * DISCONNECTED state: connect: this attempts to connect to a server on the
	 * specified network. CONNECTED state: start: starts data transmission
	 * procedure. STARTDT state: send: sends data from sendList (to be filled by
	 * user manually) stop: stops data transmission procedure. STOPDT state:
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ControllingCentre cc = new ControllingCentre(args);
		cc.launch();
	}

	public void launch() {
		String inText = "";
		int C = 0;
		byte[] input;
		InputStream in = null;
		while (true) {
			switch (context) {
			case DISCONNECTED:
				System.out.println("DISCONNECTED: " + C++);
				try {
					connection.close();
				} catch (IOException | NullPointerException e1) {
					//e1.printStackTrace();
				}
				connection = null;
				inText = listen();
				if (inText.equals("connect")) {
					try {
						connection = new ClientConnection(IP, port);
					} catch (IOException e) {
						//e.printStackTrace();
					}
				}
				if (connection != null && connection.isConnected()) {
					try {
						in = connection.getInputStream();
					} catch (IOException e) {
						e.printStackTrace();
					}
					context = FSMState.CONNECTED;
				}
				break;
			case CONNECTED:
				System.out.println("CONNECTED: " + C++);
				while (context == FSMState.CONNECTED) {
					inText = listen();
					if (inText.equals("start")) {
						startDTact();
						context = FSMState.STARTDT;
					} else if (inText.equals("disconnect")) {
						context = FSMState.DISCONNECTED;
					}
				}
				break;
			case STARTDT:
				System.out.println("STARTDT: " + C++);
				while (context == FSMState.STARTDT) {
					try {
						connection.setSoTimeout(t1);
						receive();
					} catch (SocketTimeoutException | SocketException ex) {
						context = FSMState.DISCONNECTED;
						break;
					}
					if (getTempApdu() != null) {
						if (getTempApdu().getType() == CFType.U_FORMAT) {
							if (getTempApdu().getSTARTDTcon()) {
								context = FSMState.SENDING_I;
								break;
							} else {
								context = FSMState.DISCONNECTED;
								break;
							}
						} else {
							context = FSMState.DISCONNECTED;
							break;
						}
					} else {
						context = FSMState.DISCONNECTED;
						break;
					}
				}
				break;
			case RECEIVING_I:
				System.out.println("RECEIVING_I: " + C++);
				int n = getReceiveList().size();
				while (context == FSMState.RECEIVING_I) {
					// entry begin
					getReceiveList().push(getTempApdu());
					setAck(getTempApdu().getReceiveSequenceNumber());
					setVr(getVr() + 1);
					if (getAck() == getVs()) {
						clear_buffer();
					}
					if (n == getW() - 1) {
						overflow = true;
					}
					// entry end
					inText = listen();
					try {
						connection.setSoTimeout(t2);
						receive();
					} catch (SocketTimeoutException | SocketException ex) {
						context = FSMState.SENDING_S;
						break;
					}
					if (inText.equals("send")) {
						context = FSMState.SENDING_I;
					} else if (overflow) {
						n = 1;
						context = FSMState.SENDING_S;
					} else if (tempApdu.getType() == CFType.I_FORMAT) {
						if (undisturbed) {
							getReceiveList().push(getTempApdu());
							n++;
						} else {
							context = FSMState.DISCONNECTED;
						}
					} else {
					}
					// no exit action
				}
				break;
			case RECEIVING_S:
				System.out.println("RECEIVING_S: " + C++);
				while (context == FSMState.RECEIVING_S) {
					setAck(getTempApdu().getReceiveSequenceNumber());
					if (getAck() == getVs()) {
						clear_buffer();
					}
					if (listen().equals("send")) {
						context = FSMState.SENDING_I;
					}
				}
				break;
			case SENDING_I:
				System.out.println("SENDING_I: " + C++);
				while (context == FSMState.SENDING_I) {
					try {
						inText = listen();
						boolean sending = inText.equals("send");
						boolean stopping = inText.equals("stop");
						boolean userprocess = sending || stopping;
						if (userprocess) {
							if (sending) {
								try {
									send(null);
								} catch (NullPointerException e) {
									e.printStackTrace();
								}
							} else if (stopping) {
								context = FSMState.STOPDT;
							}
						} else if (in.available() >= 6) {
							setTempApdu(null);
							input = new byte[in.available()];
							in.read(input);
							String s = "";

							for (int b : input) {
								s += Octet.getBits(b, 8);
							}
							try {
								setTempApdu(Converter.decode(s));
							} catch (Exception e) {
								e.printStackTrace();
							}
							switch (getTempApdu().getType()) {
							case I_FORMAT:
								if (getTempApdu().getReceiveSequenceNumber() < getVs()) {
									context = FSMState.DISCONNECTED;
								} else {
									context = FSMState.RECEIVING_I;
								}
								break;
							case S_FORMAT:
								context = FSMState.RECEIVING_S;
								break;
							default:
								break;
							}
						} else if (((getVs() - getAck()) < k)) {
							// buffer is not empty; stay in state
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				break;
			case SENDING_S:
				System.out.println("SENDING_S:" + C++);
				// entry
				acknowledge();
				while (context == FSMState.SENDING_S) {
					// body
					try {
						connection.setSoTimeout(t3);
						receive();
					} catch (SocketTimeoutException ex) {
					} catch (SocketException e) {
						context = FSMState.DISCONNECTED;
						break;
					}
					listen();
					if (inText.equals("send")) {
						send(null);
						context = FSMState.SENDING_I;
					} else if (getTempApdu() != null) {
						if (getTempApdu().getType() == CFType.I_FORMAT) {
							if (undisturbed) {
								context = FSMState.RECEIVING_I;
							} else {
								context = FSMState.DISCONNECTED;
							}
						}
					}
					// exit
				}
				break;
			case STOPDT:
				while (context == FSMState.STOPDT) {
					// entry
					stopDTact();
					// body

					try {
						connection.setSoTimeout(t1);
						receive();
					} catch (SocketTimeoutException | SocketException ex) {
						context = FSMState.DISCONNECTED;
						break;
					}
					switch (getTempApdu().getType()) {
					case S_FORMAT:
						setAck(getTempApdu().getReceiveSequenceNumber());
						clear_buffer();
						break;
					case U_FORMAT:
						if (getTempApdu().getSTOPDTcon()) {
							context = FSMState.DISCONNECTED;
						}
						break;
					default:
						break;
					}
					// exit
				}
				break;
			default:
				break;
			}
		}

	}

	public ControllingCentre() {
		context = FSMState.DISCONNECTED;
		form = true;
		setT1(2500);
		setT2(5000);
		setT3(10000);
		setTimeout(0);
		ip = new byte[4];
		setPort(2404);
		setVs(0);
		setVr(0);
		setAck(0);
		k = 600;
		setW(400);
		IP = null;
		sendList = new Stack<>();
		setReceiveList(new Stack<>());
	}

	public int getT3() {
		return t3;
	}

	public void setT3(int t3) {
		this.t3 = t3;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public ControllingCentre(String[] args) {
		this();
		str = args[0].split("\\.");
		for (String st : str) {
			for (char ch : st.toCharArray()) {
				if (!Character.isDigit(ch)) {
					form = false;
				}
			}
		}
		if (form == false || str.length != 4) {
			System.err.print("wrong sytnax for IP address");
			System.exit(-1);
		}
		for (int i = 0; i < 4; i++) {
			ip[i] = Byte.parseByte(str[i]);
		}
		try {
			IP = InetAddress.getByAddress(ip);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
	}

	protected void startDTact() {
		try {
			OutputStream out = connection.getOutputStream();
			out.flush();
			byte[] output = new byte[6];
			for (int i = 0; i < output.length; i++) {
				output[i] = 0 - 128;
			}
			output[0] = 1 - 128;
			output[1] = 6 - 128;
			output[2] = 7 - 128;

			out.write(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void stopDTact() {
		try {
			OutputStream os = connection.getOutputStream();
			byte[] output = new byte[6];
			for (int i = 0; i < output.length; i++) {
				output[i] = 0 - 128;
			}
			output[0] = 1 - 128;
			output[1] = 6 - 128;
			output[2] = 19 - 128;

			os.write(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void testFRact() {
		try {
			OutputStream os = connection.getOutputStream();
			byte[] output = new byte[6];
			for (int i = 0; i < output.length; i++) {
				output[i] = 0 - 128;
			}
			output[0] = 1 - 128;
			output[1] = 6 - 128;
			output[2] = 67 - 128;

			os.write(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void startDTcon() {
		try {
			OutputStream os = connection.getOutputStream();
			byte[] output = new byte[6];
			for (int i = 0; i < output.length; i++) {
				output[i] = 0 - 128;
			}
			output[0] = 1 - 128;
			output[1] = 6 - 128;
			output[2] = 9 - 128;

			os.write(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void stopDTcon() {
		try {
			OutputStream out = connection.getOutputStream();
			byte[] output = new byte[6];

			output[0] = 1 - 128;
			output[1] = 6 - 128;
			output[2] = 35 - 128;
			for (int i = 3; i < output.length; i++) {
				output[i] = 0 - 128;
			}
			out.write(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void testFRcon() {
		try {
			OutputStream os = connection.getOutputStream();
			byte[] output = new byte[6];
			for (int i = 0; i < output.length; i++) {
				output[i] = 0 - 128;
			}
			output[0] = 1 - 128;
			output[1] = 6 - 128;
			output[2] = 131 - 128;

			os.write(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void acknowledge() {
		try {
			OutputStream out = connection.getOutputStream();
			byte[] output = new byte[6];
			output[0] = 1 - 128;
			output[1] = 6 - 128;
			output[2] = 1 - 128;
			output[3] = 0 - 128;
			Bit[] upper, lower;
			int i = getVr();
			String s = "";
			while (i > 1) {
				if (i % 2 == 1) {
					s = "1" + s;
				} else {
					s = "0" + s;
				}
				i = i / 2;
			}
			while (s.length() < 15) {
				s = "0" + s;
			}
			char[] ch = s.toCharArray();
			char[] up, down;
			down = new char[8];
			up = new char[8];
			for (int z = 0; z < 7; z++) {
				down[z] = ch[z];
				up[z] = ch[z + 8];
			}
			down[7] = ch[7];
			up[7] = '0';
			lower = Converter.lex(down);
			upper = Converter.lex(up);
			output[4] = (byte) (Octet.getDecimal(lower) - 128);
			output[5] = (byte) (Octet.getDecimal(upper) - 128);

			out.write(output);
		} catch (IOException | StdException | NullPointerException e) {
			e.printStackTrace();
		}
	}

	protected APDU receive() throws SocketTimeoutException {
		try {
			InputStream in = null;
			try {
				in = connection.getInputStream();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			int av = 0;
			try {
				av = in.available();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if (av >= 6) {
				int Ns;
				byte[] info = new byte[av];
				try {
					in.read(info);
				} catch (SocketTimeoutException ex) {
					throw ex;
				} catch (IOException ex) {

				}
				String bitstring = "";
				for (int i : info) {
					bitstring += Octet.getBits(i + 128, 8);
				}
				try {
					Ns = getTempApdu().getSendSequenceNumber();
				} catch (NullPointerException e) {
					Ns = 0;
				}
				setTempApdu(Converter.decode(bitstring));
				if (getTempApdu().getSendSequenceNumber() - 1 == Ns) {
					undisturbed = true;
				}
			}
		} catch (StdException | NullPointerException e) {
			e.printStackTrace();
		}
		return tempApdu;
	}

	protected void clear_buffer() {
		// TODO Auto-generated method stub
	}

	protected String listen() {
		String returnStatement = "";
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			returnStatement = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnStatement;
	}

	protected void send(APDU sendable) {
		try {
			OutputStream out = connection.getOutputStream();
			APDU apdu = sendList.pop();
			byte[] output = Converter.encode(apdu);
			out.write(output);
			setVs(getVs() + 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public APDU getTempApdu() {
		return tempApdu;
	}

	public void setTempApdu(APDU tempApdu) {
		this.tempApdu = tempApdu;
	}

	public int getT1() {
		return t1;
	}

	public void setT1(int t1) {
		this.t1 = t1;
	}

	public int getAck() {
		return Ack;
	}

	public void setAck(int ack) {
		Ack = ack;
	}

	public int getVs() {
		return Vs;
	}

	public void setVs(int vs) {
		Vs = vs;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public Stack<APDU> getReceiveList() {
		return receiveList;
	}

	public void setReceiveList(Stack<APDU> receiveList) {
		this.receiveList = receiveList;
	}

	public int getVr() {
		return Vr;
	}

	public void setVr(int vr) {
		Vr = vr;
	}

	public int getT2() {
		return t2;
	}

	public void setT2(int t2) {
		this.t2 = t2;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}