package iec_60870_5_104.apdu.asdu.object;

import converters.Octet;

/**
 * TODO unlikely to be implemented; overriden by Data Unit Type
 * 
 * @author ar421
 *
 */
public enum Information_Object_Type {
	DEFAULT;

	static Information_Object_Type determine(Octet data) {

		return DEFAULT;
	}
}