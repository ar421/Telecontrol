package iec_60870_5_104.apdu.asdu.dui;

import converters.Bit;
import converters.Octet;

/**
 * TODO documentation
 * 
 * @author ar421
 *
 */
public class Cause_of_Transmission {
	@Override
	public String toString() {
		return "CAUSEOFTRANSMISSION(" + cause_of_transmission + "),ORIGINATORADDRESS(" + originator_address + ")";
	}

	public int cause_of_transmission, originator_address;
	public Bit pnbit, testbit;
	private static int c = 1;
	public static final int per_cyc = c++, back = c++, spont = c++, init = c++, req = c++, act = c++, actcon = c++,
			deact = c++, deactcon = c++, actterm = c++, retrem = c++, retloc = c++, file = c++, inrogen = (c = 20),
			inro1 = c++, inro2 = c++, inro3 = c++, inro4 = c++, inro5 = c++, inro6 = c++, inro7 = c++, inro8 = c++,
			inro9 = c++, inro10 = c++, inro11 = c++, inro12 = c++, inro13 = c++, inro14 = c++, inro15 = c++,
			inro16 = c++, reqcogen = c++, reqco1 = c++, reqco2 = c++, reqco3 = c++, reqco4 = c++;

	public Cause_of_Transmission(Octet[] data) {
		Octet[] d = data;
		Octet cause, address;
		cause = d[0];
		Bit[] bits = cause.getBits(), cbits = new Bit[6];
		for (int i = 2; i < 8; i++) {
			cbits[i - 2] = bits[i];
		}
		cause_of_transmission = Octet.getDecimal(cbits);
		pnbit = bits[1];
		testbit = bits[0];
		try {
			address = d[1];
			originator_address = Octet.getDecimal(address.getBits());
		} catch (ArrayIndexOutOfBoundsException e) {
			originator_address = 0;
		}
	}
}