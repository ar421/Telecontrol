package iec_60870_5_104.apdu.asdu.dui;

import converters.Octet;
import iec_60870_5_104.apdu.ASDU;

public class Data_Unit_Type {
	@Override
	public String toString() {
		String string = "TYPEIDENT(" + getTi().toString() + "),";
		string += "ASDULENGTH(" + length_of_asdu + "),";
		string += "VARSTRUCTQUALIFIER(" + getVsq().toString() + ")";
		return string;
	}

	private Type_Identification ti;
	int length_of_asdu;
	private Variable_Structure_Qualifier vsq;

	public Data_Unit_Type(ASDU asdu) {
		Octet[] d = asdu.getData();
		setTi(Type_Identification.determine(d[ASDU.dc++]));
		length_of_asdu = asdu.getData().length;
		setVsq(new Variable_Structure_Qualifier(d[ASDU.dc++]));
	}

	public boolean isDefinitive() {
		// TODO renders Information_Object_Address redundant
		return true;
	}

	public Variable_Structure_Qualifier getVsq() {
		return vsq;
	}

	public void setVsq(Variable_Structure_Qualifier vsq) {
		this.vsq = vsq;
	}

	public Type_Identification getTi() {
		return ti;
	}

	public void setTi(Type_Identification ti) {
		this.ti = ti;
	}
}