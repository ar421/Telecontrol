package iec_60870_5_104.apdu;

import converters.Bit;
import converters.Octet;
import exceptions.NotBinaryException;

public class ControlField {
	@Override
	public String toString() {
		String string = "";
		
		switch(getType()) {
		case I_FORMAT:
			string += "I_FORMAT";
			break;
		case S_FORMAT:
			string += "S_FORMAT";
			break;
		case U_FORMAT:
			string += "U_FORMAT";
			break;
		}
		
		return string;
	}

	int NS, NR;
	private Octet[] octets;
	private CFType type;
	boolean[] sdt, pdt, tfr;
	boolean STARTDTcon;
	boolean STARTDTact;
	boolean STOPDTcon;
	boolean STOPDTact;
	boolean TESTFRcon;
	boolean TESTFRact;
	/**
	 * creates a new ControlField instance
	 * @param cf1
	 * @param cf2
	 * @param cf3
	 * @param cf4
	 * @throws NotBinaryException 
	 */
	public ControlField(Octet cf1, Octet cf2, Octet cf3, Octet cf4) throws NotBinaryException {
		setOctets(new Octet[]{cf1,cf2,cf3,cf4});
		determine(getOctets());
	}
	
	CFType determine(Octet[] cfs) throws NotBinaryException {
		Bit[] b1, b2;
		switch (cfs[0].getBits()[7]) {
		case ZERO:
			//TODO test
			System.out.println("cfs[0].bits[7]: " + cfs[0].getBits()[7].toString() + "\n" + "cfs[0]:" + cfs[0].toString());
			//test
			setType(CFType.I_FORMAT);
			b1 = cfs[1].getBits();
			b2 = new Bit[7];
			for (int i = 0; i < 7; i++) {
				b2[i] = cfs[0].getBits()[i];
			}
			NS = Octet.getDecimal(Octet.concat(b1,b2));
			b1 = cfs[3].getBits();
			b2 = new Bit[7];
			for (int i = 0; i < 7; i++) {
				b2[i] = cfs[2].getBits()[i];
			}
			NR = Octet.getDecimal(Octet.concat(b1, b2));
			break;
		case ONE:
			switch (cfs[0].getBits()[6]) {
			case ZERO:
				// XXX S_Format
				setType(CFType.S_FORMAT);
				b1 = cfs[3].getBits();
				b2 = new Bit[7];
				for (int i = 0; i < 7; i++) {
					b2[i] = cfs[2].getBits()[i];
				}
				NR = Octet.getDecimal(Octet.concat(b1, b2));
				break;
			case ONE:
				// XXX U_Format
				setType(CFType.U_FORMAT);
				sdt = new boolean[2];
				sdt[0] = cfs[0].getBits()[4].toBoolean();
				sdt[1] = cfs[0].getBits()[5].toBoolean();
				pdt = new boolean[2];
				pdt[0] = cfs[0].getBits()[2].toBoolean();
				pdt[1] = cfs[0].getBits()[3].toBoolean();
				tfr = new boolean[2];
				tfr[0] = cfs[0].getBits()[0].toBoolean();
				tfr[1] = cfs[0].getBits()[1].toBoolean();
				
				STARTDTcon = (sdt[0] && !sdt[1]) && (!pdt[0] && !pdt[1]) && (!tfr[0] && !tfr[1]);
				STARTDTact = (!sdt[0] && sdt[1]) && (!pdt[0] && !pdt[1]) && (!tfr[0] && !tfr[1]);
				STOPDTcon= (!sdt[0] && !sdt[1]) && (pdt[0] && !pdt[1]) && (!tfr[0] && !tfr[1]);
				STOPDTact = (!sdt[0] && !sdt[1]) && (!pdt[0] && pdt[1]) && (!tfr[0] && !tfr[1]);
				TESTFRcon = (!sdt[0] && !sdt[1]) && (!pdt[0] && !pdt[1]) && (tfr[0] && !tfr[1]);
				TESTFRact = (!sdt[0] && !sdt[1]) && (!pdt[0] && !pdt[1]) && (!tfr[0] && tfr[1]);
				break;
			}
			break;
		}
		return getType();
	}

	public Octet[] getOctets() {
		return octets;
	}

	public void setOctets(Octet[] octets) {
		this.octets = octets;
	}

	public CFType getType() {
		return type;
	}

	public void setType(CFType type) {
		this.type = type;
	}
}