package iec_60870_5_104.apdu;

import converters.Octet;
import exceptions.DataUnitIDException;
import iec_60870_5_104.apdu.asdu.dui.Cause_of_Transmission;
import iec_60870_5_104.apdu.asdu.dui.Common_Address;
import iec_60870_5_104.apdu.asdu.dui.Data_Unit_Type;

/**
 * Data Unit Identifier: adds uniqueness to the ASDU by providing certain
 * Octets.
 * 
 * @author ar421
 *
 */
public class Data_Unit_Identifier {
	@Override
	public String toString() {
		return "DATAUNITTYPE(" + getDut().toString() + "),CAUSEOFTRANSMISSION(" + cot.toString() + "),COMMONADDRESS(" + ca.toString() + ")";
	}

	private Data_Unit_Type dut;
	Cause_of_Transmission cot;
	Common_Address ca;

	/**
	 * creates a new DataUnitIdent instance
	 * 
	 * @param data
	 * @param a
	 *            //number of octets in the common address
	 * @param b
	 *            //number of octets in the cause of transmission
	 * @throws DataUnitIDException
	 */
	Data_Unit_Identifier(ASDU asdu, int a, int b) throws DataUnitIDException {
		Octet[] d = asdu.getData();
		setDut(new Data_Unit_Type(asdu));
		switch (b) {
		case 1:
			cot = new Cause_of_Transmission(new Octet[] { d[ASDU.dc++] });
			break;
		case 2:
			cot = new Cause_of_Transmission(new Octet[] { d[ASDU.dc++], d[ASDU.dc++] });
			break;
		}
		switch (a) {
		case 1:
			ca = new Common_Address(new Octet[] { d[ASDU.dc++] });
			break;
		case 2:
			ca = new Common_Address(new Octet[] { d[ASDU.dc++], asdu.getData()[ASDU.dc++] });
			break;
		}
	}

	public Data_Unit_Type getDut() {
		return dut;
	}

	public void setDut(Data_Unit_Type dut) {
		this.dut = dut;
	}
}