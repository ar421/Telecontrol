package converters;

import exceptions.APDUSizeOutOfBoundsException;
import exceptions.IllFormedOctetsException;
import exceptions.NotBinaryException;
import exceptions.StdException;
import iec_60870_5_104.apdu.APCI;
import iec_60870_5_104.apdu.APDU;
import iec_60870_5_104.apdu.ASDU;
import iec_60870_5_104.apdu.ControlField;

/**
 * states in an FSM for determining the type and the mapping of an APDU
 * 
 * @author ar421
 */
enum APCiState {
	START_68H, LENGTH, CONTROL_FIELD, ASDU, END
}

/**
 * the opposite of the Modulator class: turns bits of data into high level
 * representations adhering to the 104 standard - namely, APDUs
 * 
 * @author ar421
 */
public class Converter {
	/// **
	// * the purpose of this main method is just for testing.
	// *
	// * @param args
	// */
	// public static void main(String[] args) { try { //decode(args[0]); } catch
	// (Exception e) { e.printStackTrace(); } }
	/**
	 * converts received binary signal to a parse-list of either APDUs or
	 * functions
	 * 
	 * @param rx
	 * @throws Exception
	 */
	public static APDU decode(String rx) throws StdException {
		char[] rxarr = rx.toCharArray();
		Bit[] bits = lex(rxarr);
		Octet[] octets = parse(bits);
		//TEST test
		/*for (Octet o : octets) {
			System.out.println("test: " + o.toString());
		}*/
		//test
		if (octets.length > 253 || octets.length < 4) {
			throw new APDUSizeOutOfBoundsException();
		}
		APDU apdu = generate(octets);

		return apdu;
	}
	
	/**
	 * converts transmit APDU to binary bitstring to be sent via TCP
	 * @param apdu
	 * @return
	 */
	public static byte[] encode(APDU apdu) {
		byte[] returnValue = new byte[apdu.getAsdu().getData().length + 6];
		returnValue[0] = (byte) (Octet.getDecimal(apdu.getApci().getStart68H().getBits()) - 128);
		returnValue[1] = (byte) (Octet.getDecimal(apdu.getApci().getLength().getBits()) - 128);
		int counter = 2;
		for (Octet octet : apdu.getApci().getCf().getOctets()) {
			returnValue[counter++] = (byte) (Octet.getDecimal(octet.getBits()) - 128);
		}
		switch (apdu.getType()) {
		case I_FORMAT:
			counter = 6;
			for (Octet octet : apdu.getAsdu().getData()) {
				returnValue[counter++] = (byte) (Octet.getDecimal(octet.getBits()) - 128);
			}
			break;
		default:
			break;
		}
		return returnValue;
	}

	/**
	 * 
	 * @param rxarr
	 * @return
	 * @throws NotBinaryException
	 * @throws NullPointerException
	 */
	public static Bit[] lex(char[] rxarr) throws StdException, NullPointerException {
		Bit[] input_bits = new Bit[rxarr.length];
		int counter = 0;
		for (char rxa : rxarr) {
			if (rxa == '0') {
				input_bits[counter] = Bit.ZERO;
			} else if (rxa == '1') {
				input_bits[counter] = Bit.ONE;
			} else {
				throw new NotBinaryException();
			}
			counter++;
		}
		return input_bits;
	}

	/**
	 * converts a list of Bit objects into a list of Octets
	 * 
	 * @param bits
	 * @return
	 * @throws IllFormedOctetsException
	 */
	public static Octet[] parse(Bit[] bits) throws StdException {
		if (bits.length % 8 != 0 && bits.length < 8) {
			throw new IllFormedOctetsException();
		}
		int a, num_octs;
		a = 0;
		num_octs = bits.length / 8;
		Octet tempOct;
		Octet[] octets = new Octet[num_octs];
		for (int c = 0; c < num_octs; c++) {
			Bit[] tempBit = new Bit[8];
			for (int b = 0; b < 8; b++, a++) {
				tempBit[b] = bits[a];
			}
			tempOct = new Octet(tempBit);
			octets[c] = tempOct;
		}
		return octets;
	}

	/**
	 * generates an APDU with the taken octets
	 * 
	 * @param octets
	 * @return
	 * @throws NotBinaryException 
	 */
	public static APDU generate(Octet[] octets) throws StdException {
		APDU apdu = null;
		// APCI
		APCI apci = null;
		Octet start68h = null, length = null, cf1 = null, cf2 = null, cf3 = null, cf4 = null;
		ControlField cf = null;
		// APCI
		// ASDU
		ASDU asdu = null;
		// ASDU
		int pc = 0;// XXX program counter
		// FSM
		APCiState state = APCiState.START_68H;
		for (int xen = 0; xen < 255; xen++) {
			switch (state) {
			case START_68H:
				start68h = octets[pc];
				pc = Octet.getDecimal(start68h.getBits());
				state = APCiState.LENGTH;
				break;
			case LENGTH:
				length = octets[pc++];
				state = APCiState.CONTROL_FIELD;
				break;
			case CONTROL_FIELD:
				cf1 = octets[pc++];
				cf2 = octets[pc++];
				cf3 = octets[pc++];
				cf4 = octets[pc++];
				cf = new ControlField(cf1, cf2, cf3, cf4);
				switch (cf.getType()) {
				case I_FORMAT:
					state = APCiState.ASDU;
					break;
				default:
					state = APCiState.END;
					break;
				}
				break;
			case ASDU:
				//System.out.println(cf1.toString() + " " + cf2.toString() + " " + cf3.toString() + " " + cf4.toString());
				Octet[] data = new Octet[Octet.getDecimal(length.getBits()) - 6];
				int populator = 0;
				while (populator < data.length) {
					data[populator++] = octets[pc++];
				}
				try {
					asdu = new ASDU(data, 2, 3);
				} catch (StdException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				state = APCiState.END;
				break;
			case END:
				apci = new APCI(start68h, length, cf);
				apdu = new APDU(apci, asdu);
				break;
			}
		}
		return apdu;
	}
}
