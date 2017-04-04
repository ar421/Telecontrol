package iec_60870_5_104.apdu.asdu.dui;

import converters.Octet;

/**
 * 
 * 
 * @author ar421
 */
public class Common_Address {
	
	@Override
	public String toString() {
		return "ADDRESS(" + address + ")";
	}

	int address;

	public Common_Address(Octet[] data) {
		try {
			address = Octet.getDecimal(Octet.concat(data[0].getBits(), data[1].getBits()));
		} catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
			address = Octet.getDecimal(data[0].getBits());
		}
	}
}