package iec_60870_5_104.apdu.asdu.dui;

import converters.Bit;
import converters.Octet;

/**
 * TODO documentation
 * 
 * @author ar421
 *
 */
public class Variable_Structure_Qualifier {
	@Override
	public String toString() {
		return "NUMBEROFADDRESSABLEUNITS(" + getNumber_of_addressables() + "),ADDRESSINGMODE(" + isAddressing_mode() + ")";
	}
	private int number_of_addressables; // number of information objects or elements
	private boolean addressing_mode; // false: addressable units are objects; true: addressable units are elements in one object
	Variable_Structure_Qualifier(Octet data) {
		Bit[] d = data.getBits();
		setNumber_of_addressables(Octet.getDecimal(new Bit[] { d[1], d[2], d[3], d[4], d[5], d[6], d[7] }));
		switch(d[0]) {
		case ZERO:
			setAddressing_mode(false);
			break;
		case ONE:
			setAddressing_mode(true);
			break;
		}
	}
	public boolean isAddressing_mode() {
		return addressing_mode;
	}
	public void setAddressing_mode(boolean addressing_mode) {
		this.addressing_mode = addressing_mode;
	}
	public int getNumber_of_addressables() {
		return number_of_addressables;
	}
	public void setNumber_of_addressables(int number_of_addressables) {
		this.number_of_addressables = number_of_addressables;
	}
}
