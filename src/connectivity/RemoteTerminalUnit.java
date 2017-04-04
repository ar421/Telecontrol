package connectivity;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import iec_60870_5_104.apdu.CFType;

public class RemoteTerminalUnit extends ControllingCentre {
	private ServerConnection server;

	public static void main(String[] args) {
		RemoteTerminalUnit rtu = new RemoteTerminalUnit();
		rtu.launch();
	}
	
	@Override
	public void launch() {
		int C = 0;
		while (true) {
			switch (context) {
			case DISCONNECTED:
				System.out.println("DISCONNECTED: " + C++);
				setTempApdu(null);
				try {
					try {
						server.close();
					} catch (IOException | NullPointerException e) {
						//e.printStackTrace();
					}
					server = new ServerConnection(getPort());
				} catch (IOException e) {
					//e.printStackTrace();
				}
				server.launch();
				if (server.getClient() != null) {
					connection = server.getClient();
					context = FSMState.CONNECTED;
				}
				break;
			case CONNECTED:
				System.out.println("CONNECTED: " + C++);
				while (context == FSMState.CONNECTED) {
					try {
						connection.setSoTimeout(t1);
						receive();
					} catch (SocketTimeoutException | SocketException e) {
						context = FSMState.DISCONNECTED;
						break;
					}
					if (getTempApdu() != null) {
						if (getTempApdu().getType() == CFType.U_FORMAT) {
							if (getTempApdu().getSTARTDTact()) {
								context = FSMState.STARTDT;
								break;
							} else if (getTempApdu().getTESTFRact()) {
								testFRcon();
							} else {
								context = FSMState.DISCONNECTED;
							}
						}
					}
				}
				break;
			case STARTDT:
				System.out.println("STARTDT: " + C++);
				while (context == FSMState.STARTDT) {
					startDTcon();
					try {
						connection.setSoTimeout(t1);
						receive();
						
					} catch (SocketTimeoutException | SocketException e) {
						context = FSMState.DISCONNECTED;
						break;
					}
					if (getTempApdu() != null) {
						switch (getTempApdu().getType()) {
						case I_FORMAT:
							context = FSMState.RECEIVING_I;
							break;
						default:
							context = FSMState.DISCONNECTED;
							break;	
						}
					} else {
						context = FSMState.DISCONNECTED;
					}
				}
				break;
			case RECEIVING_I:
				System.out.println("RECEIVING_I: " + C++);
				int n = 1;
				while (context == FSMState.RECEIVING_I) {
					setAck(getTempApdu().getReceiveSequenceNumber());
					if (getAck() == getVs()) {
						clear_buffer();
					}
					if (n == getW() - 1) {
						overflow = true;
					}
					try {
						connection.setSoTimeout(t2);
						receive();
					} catch (SocketTimeoutException e) {
						context = FSMState.SENDING_S;
						break;
					} catch (SocketException e) {
						context = FSMState.DISCONNECTED;
					}
					if (getTempApdu() != null) {
						if (getTempApdu().getType() == CFType.I_FORMAT) {
							if (undisturbed) {
								getReceiveList().push(getTempApdu());
								n++;
								setVr(getVr() + 1);
							} else {
								context = FSMState.DISCONNECTED;
								break;
							}
						}
					}
				}
				break;
			case SENDING_S:
				System.out.println("SENDING_S: " + C++);
				acknowledge();
				while (context == FSMState.SENDING_S) {
					try {
						connection.setSoTimeout(t3);
						receive();
					} catch (SocketTimeoutException e) {
					} catch (SocketException e) {
						context = FSMState.DISCONNECTED;
						break;
					}
					if (getTempApdu() != null) {
						if (undisturbed) {
							context = FSMState.RECEIVING_I;
						} else {
							context = FSMState.DISCONNECTED;
						}
					}
				}
				break;
			case SENDING_I:
				System.out.println("SENDING_I" + C++);
				break;
			case RECEIVING_S:
				System.out.println("RECEIVING_S" + C++);
				break;
			case STOPDT:
				System.out.println("STOPDT: " + C++);
				break;
			default:
				break;
			}
		}
	}

	public RemoteTerminalUnit() {
		super();
	}
}