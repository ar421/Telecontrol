package iec_60870_5_104.apdu;

import converters.Octet;

/**
 * APCI, or Application Protocol Control Information - is a bus of bytes containing information about the APDU in terms of
 * size (length of APDU in bytes), type (through the use of control fields) and the starting position.
 * @author ar421
 *
 */
public class APCI {
	@Override
	public String toString() {
		String string = "APCI{";
		string += "START68H(" + getStart68H().toString() + "),";
		string += "LENGTH(" + getLength().toString() + "),";
		string += "CONTROLFIELD(" + getCf().toString() + ")";
		string += "}";
		return string;
	}
	private Octet start68H;
	private Octet length;
	private ControlField cf;
	/**
	 * creates a new APCI instance
	 * @param start68H
	 * @param length
	 * @param cf
	 */
	public APCI(Octet start68H, Octet length, ControlField cf) {
		this.setStart68H(start68H);
		this.setLength(length);
		this.setCf(cf);
	}
	public Octet getStart68H() {
		return start68H;
	}
	public void setStart68H(Octet start68h) {
		start68H = start68h;
	}
	public Octet getLength() {
		return length;
	}
	public void setLength(Octet length) {
		this.length = length;
	}
	public ControlField getCf() {
		return cf;
	}
	public void setCf(ControlField cf) {
		this.cf = cf;
	}
}