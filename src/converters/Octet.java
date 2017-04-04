package converters;

/**
 * a 'Byte' containing 8 'Bit' objects XXX this might be replaced soon with the byte primitive and its bit-representation
 * @author ar421
 *
 */
public class Octet {
	private Bit[] bits = new Bit[8];
	/**
	 * creates a new Octet instance
	 * @param bits
	 */
	public Octet(Bit[] bits) {
		try {
			this.setBits(bits);
		} catch (ArrayIndexOutOfBoundsException e) {

		}
	}
	/**
	 * gets base 10 representation of a list of concatenated bits in an array
	 * @param bits
	 * @return
	 */
	public static int getDecimal(Bit[] bits) {
		int rt = 0;
		for (int c = 0; c < bits.length; c++) {
			if (bits[c] == Bit.ONE) {
				rt += (int) Math.pow(2, bits.length - (c + 1));
			}
		}
		return rt;
	}
	
	public static String getBits(int integer, int length) {
		int i = integer;
		String s = "";
		while (i >= 1) {
			if (i % 2 == 1) {
				s = "1" + s;
			} else {
				s = "0" + s;
			}
			i = i / 2;
		}
		while (s.length() < length) {
			s = "0" + s;
		}
		return s;
	}
	/**
	 * concatenates two Bit arrays into one.
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static Bit[] concat(Bit[] b1, Bit[] b2) {
		final Bit[] result = new Bit[b1.length + b2.length];
		for (int i = 0; i < b1.length; i++) {
			result[i] = b1[i];
		}
		for (int i = b1.length; i < b1.length + b2.length; i++) {
			result[i] = b2[i-b1.length];
		}
		return result;
	}

	@Override
	/**
	 * returns a string of 1s and 0s representing the octet
	 */
	public String toString() {
		String rtstr = "";
		for (Bit bit : getBits()) {
			rtstr += bit.toString();
		}
		return rtstr;
	}

	@Override
	/**
	 * decides if two octets are bitwise-equal
	 */
	public boolean equals(Object octet) {
		if (!(octet instanceof Octet) || ((Octet) octet).getBits().length != this.getBits().length) {
			return false;
		}
		for (int i = 0; i < this.getBits().length; i++) {
			if (this.getBits()[i] != ((Octet) octet).getBits()[i]) {
				return false;
			}
		}
		return true;
	}
	public Bit[] getBits() {
		return bits;
	}
	public void setBits(Bit[] bits) {
		this.bits = bits;
	}
}