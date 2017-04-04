package iec_60870_5_104.apdu.asdu.object;
/**
 * type of information element as alluded to earlier
 * 
 * @author ar421
 *
 */
public enum Information_Element_Type {
	UI("UNSIGNED INTEGER"), I("INTEGER"), UF("UNSIGNED FIXED POINT NUMBER"), F("FIXED POINT NUMBER"), R("REAL"), BS(
			"BITSTRING"), OS("OCTETSTRING"), CP("COMPOUND INFORMATION ELEMENT"), E("EMPTY");

	private String type;

	/**
	 * creates a new Type enum instance
	 * 
	 * @param type
	 */
	private Information_Element_Type(String type) {
		this.type = type;
	}

	@Override
	/**
	 * returns the human-readable description of the type
	 * 
	 * @return type
	 */
	public String toString() {
		return type;
	}
}