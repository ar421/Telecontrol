package converters;

import exceptions.NotBinaryException;

/**
 * an ENUM object to represent individual bits
 * @author ar421
 *
 */
public enum Bit {
	ZERO, ONE;

	@Override
	/**
	 * returns a string which is easily convertible to an integer of either 0 or 1
	 */
	public String toString() {
		switch (this) {
		case ZERO:
			return "0";
		case ONE:
			return "1";
		}
		return null;
	}
	
	public boolean toBoolean() throws NotBinaryException {
		switch(this) {
		case ZERO:
			return false;
		case ONE:
			return true;
		default: throw new NotBinaryException();
		}
	}
}