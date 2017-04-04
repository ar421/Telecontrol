package iec_60870_5_104.apdu.asdu.object;

import converters.Octet;
import exceptions.InformationObjectAddressException;
import iec_60870_5_104.apdu.ASDU;

/**
 * A way to distinguish information objects TODO implementation
 * 
 * @author ar421
 *
 */
public class Information_Object_Identifier {
	Information_Object_Type iot; // TODO unlikely to be implemented
	Information_Object_Address ioa;

	/**
	 * creates a new InformationObjectIdentifier
	 * 
	 * @param data
	 * @throws InformationObjectAddressException
	 */
	public Information_Object_Identifier(ASDU asdu, int c) throws InformationObjectAddressException {
		Octet[] d = asdu.getData();
		if (!asdu.getData_unit_identifier().getDut().isDefinitive()) {
			iot = Information_Object_Type.determine(d[ASDU.dc++]);
		}
		ioa = new Information_Object_Address(d, c);
	}
}