package iec_60870_5_104.apdu;

import converters.Converter;
import converters.Octet;
import exceptions.StdException;

/**
 * APDU or Application Protocol Data Unit - is the representation of data to be exchanged between clients and servers in the 104 standard.
 * It contains the APCI (header information) and the ASDU (actual information).
 * @author ar421
 *
 */
public class APDU {
	private APCI apci;
	private ASDU asdu;
	/**
	 * creates a new instance of APDU
	 * @param apci
	 * @param asdu
	 */
	public APDU(APCI apci, ASDU asdu) {
		this.setApci(apci);
		this.setAsdu(asdu);
	}
	/**
	 * A much simpler constructor used as API for programmers
	 * @param format - one of three char values: I for I_FORMAT; S for S_FORMAT; U for U_FORMAT
	 * @param Ns - Send Sequence Number
	 * @param Nr - Receive Sequence Number
	 * @param start - starting point of APDU
	 * @param length - length of APDU
	 * @param data - ASDU object if format='I'
	 * @throws StdException 
	 * @throws NullPointerException 
	 */
	public APDU(String format, int Ns, int Nr, int start, int length, ASDU data) throws NullPointerException, StdException {
		//TODO API for IEC programmers
		String val = "";
		Octet s, l;
		ControlField c;
		val += Octet.getBits(start, 8);
		val += Octet.getBits(length, 8);
		if (format.equals("I_FORMAT")) {
			val += Octet.getBits(Ns, 16)
					+ Octet.getBits(Nr, 16);
			asdu = data;
		} else if (format.equals("S_FORMAT")) {
			val += "00000001"
					+ "00000000"
					+ Octet.getBits(Nr * 2, 16);
		} else if (format.equals("U_STARTact")) {
			val += "00000111"
					+ "00000000"
					+ "00000000"
					+ "00000000";
		} else if (format.equals("U_STOPact")) {
			val += "00010011"
					+ "00000000"
					+ "00000000"
					+ "00000000";
		} else if (format.equals("U_TESTact")) {
			val += "01000011"
					+ "00000000"
					+ "00000000"
					+ "00000000";
		}else if (format.equals("U_STARTcon")) {
			val += "00001011"
					+ "00000000"
					+ "00000000"
					+ "00000000";
		} else if (format.equals("U_STOPcon")) {
			val += "00100011"
					+ "00000000"
					+ "00000000"
					+ "00000000";
		} else if (format.equals("U_TESTcon")) {
			val += "10000011"
					+ "00000000"
					+ "00000000"
					+ "00000000";
		} else {
			System.err.print("wrong APDU parameters");
		}
		Octet[] octets = Converter.parse(Converter.lex(val.toCharArray()));
		ControlField cf = new ControlField(octets[2], octets[3], octets[4], octets[5]);
		setApci(new APCI(octets[0], octets[1], cf));
	}
	@Override
	public String toString() {
		String string = "APDU{";
		string += getApci().toString() + ",";
		string += getAsdu().toString();
		string += "}";
		return string;
	}
	public CFType getType() {
		return getApci().getCf().getType();
	}
	public int getReceiveSequenceNumber() {
		return getApci().getCf().NR;
	}
	public int getSendSequenceNumber() {
		return getApci().getCf().NS;
	}
	public boolean getSTOPDTcon() {
		return getApci().getCf().STOPDTcon;
	}
	public boolean getSTOPDTact() {
		return getApci().getCf().STOPDTact;
	}
	public boolean getSTARTDTcon() {
		return getApci().getCf().STARTDTcon;
	}
	public boolean getSTARTDTact() {
		return getApci().getCf().STARTDTact;
	}
	public boolean getTESTFRcon() {
		return getApci().getCf().TESTFRcon;
	}
	public boolean getTESTFRact() {
		return getApci().getCf().TESTFRact;
	}
	public ASDU getAsdu() {
		return asdu;
	}
	public void setAsdu(ASDU asdu) {
		this.asdu = asdu;
	}
	public APCI getApci() {
		return apci;
	}
	public void setApci(APCI apci) {
		this.apci = apci;
	}
}
