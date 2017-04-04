package iec_60870_5_104.apdu.asdu.object;

import converters.Bit;
import converters.Octet;
import exceptions.InformationObjectAddressException;
import iec_60870_5_104.apdu.ASDU;

/**
 * If called-in for Data_Unit_Type or Information_Object_Type
 * 
 * @author ar421
 *
 */
public class Information_Object_Address {
	int address;

	Information_Object_Address(Octet[] data, int system_param) throws InformationObjectAddressException {
		Octet[] ioa;
		Bit[] a, b, c;
		switch (system_param) {
		case 1:
			ioa = new Octet[] { data[ASDU.dc++] };
			a = ioa[0].getBits();
			address = Octet.getDecimal(a);
			break;
		case 2:
			ioa = new Octet[] { data[ASDU.dc++], data[ASDU.dc++] };
			a = ioa[0].getBits();
			b = ioa[1].getBits();
			address = Octet.getDecimal(Octet.concat(a, b));
			break;
		case 3:
			ioa = new Octet[] { data[ASDU.dc++], data[ASDU.dc++], data[ASDU.dc++] };
			a = ioa[0].getBits();
			b = ioa[1].getBits();
			c = ioa[2].getBits();
			address = Octet.getDecimal(Octet.concat(Octet.concat(a, b), c));
			break;
		default:
			throw new InformationObjectAddressException();
		}
	}
}