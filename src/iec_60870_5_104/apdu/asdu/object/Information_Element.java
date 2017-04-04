package iec_60870_5_104.apdu.asdu.object;

import converters.Bit;
import converters.Octet;
import exceptions.ElementTypeException;
import exceptions.NotBinaryException;
import iec_60870_5_104.apdu.ASDU;

/**
 * Information Element: pure-data data; either a number or a compound of
 * compounds of numbers or other combinations TODO implementation
 * 
 * @author ar421
 *
 */
public abstract class Information_Element {

	Information_Element_Type type;
	float value;
	Information_Element next_element;

	/**
	 * creates a new InformationElement instance
	 * 
	 * @param data
	 * @throws ElementTypeException
	 */
	// Information_Element(Octet[] data, Data_Unit_Identifier duid) throws
	// ElementTypeException {
	// }

	/**
	 * decides the InformationElement Type based on the taken octet
	 * 
	 * @param data
	 * @return
	 * @throws NotBinaryException
	 * @throws Exception
	 */
	public static Information_Element createInfoElem(ASDU asdu) throws ElementTypeException, NotBinaryException {
		Information_Element element = null;
		// TODO ASDU
		switch (asdu.getData_unit_identifier().getDut().getTi()) {
		case C_BO_NA_1:
			element = new BSI(asdu);
			break;
		case C_BO_TA_1:
			element = new BSI(asdu);
			element.next_element = new CP56Time2a(asdu);
			break;
		case C_CI_NA_1:
			element = new QCC(asdu);
			break;
		case C_CS_NA_1:
			element = new CP56Time2a(asdu);
			break;
		case C_DC_NA_1:
			element = new DCO(asdu);
			element.next_element = new QOC(asdu);
			break;
		case C_DC_TA_1:
			element = new DCO(asdu);
			element.next_element = new QOC(asdu);
			element.next_element.next_element = new CP56Time2a(asdu);
			break;
		case C_IC_NA_1:
			element = new QOI(asdu);
			break;
		case C_RC_NA_1:
			element = new RCO(asdu);
			element.next_element = new QOC(asdu);
			break;
		case C_RC_TA_1:
			element = new RCO(asdu);
			element.next_element = new QOC(asdu);
			element.next_element.next_element = new CP56Time2a(asdu);
			break;
		case C_RD_NA_1:
			// NOP read command
			break;
		case C_RP_NA_1:
			element = new QRP(asdu);
			break;
		case C_SC_NA_1:
			element = new SCO(asdu);
			element.next_element = new QOC(asdu);
			break;
		case C_SC_TA_1:
			element = new SCO(asdu);
			element.next_element = new QOC(asdu);
			element.next_element.next_element = new CP56Time2a(asdu);
			break;
		case C_SE_NA_1:
			element = new NVA(asdu);
			element.next_element = new QOS(asdu);
			break;
		case C_SE_NB_1:
			element = new SVA(asdu);
			element.next_element = new QOS(asdu);
			break;
		case C_SE_NC_1:
			element = new R32IEEE_STD_754(asdu);
			element.next_element = new QOS(asdu);
			break;
		case C_SE_TA_1:
			element = new NVA(asdu);
			element.next_element = new QOS(asdu);
			element.next_element.next_element = new CP56Time2a(asdu);
			break;
		case C_SE_TB_1:
			element = new SVA(asdu);
			element.next_element = new QOS(asdu);
			element.next_element.next_element = new CP56Time2a(asdu);
			break;
		case C_SE_TC_1:
			element = new R32IEEE_STD_754(asdu);
			element.next_element = new QOS(asdu);
			element.next_element.next_element = new CP56Time2a(asdu);
			break;
		case C_TS_TA_1:
			element = new TSC(asdu);
			element.next_element = new CP56Time2a(asdu);
			break;
		case F_AF_NA_1:
			element = new NOF(asdu);
			element.next_element = new NOS(asdu);
			element.next_element.next_element = new AFQ(asdu);
			break;
		case F_DR_TA_1:
			element = new NOF(asdu);
			element.next_element = new LOF(asdu);
			element.next_element.next_element = new SOF(asdu);
			element.next_element.next_element.next_element = new CP56Time2a(asdu);
			break;
		case F_FR_NA_1:
			element = new NOF(asdu);
			element.next_element = new LOF(asdu);
			element.next_element.next_element = new FRQ(asdu);
			break;
		case F_LS_NA_1:
			element = new NOF(asdu);
			element.next_element = new NOS(asdu);
			element.next_element.next_element = new LSQ(asdu);
			element.next_element.next_element.next_element = new CHS(asdu);
			break;
		case F_SC_NA_1:
			element = new NOF(asdu);
			element.next_element = new NOS(asdu);
			element.next_element.next_element = new SCQ(asdu);
			break;
		case F_SC_NB_1:
			element = new NOF(asdu);
			element.next_element = new CP56Time2a(asdu);
			element.next_element.next_element = new CP56Time2a(asdu);
			break;
		case F_SG_NA_1:
			element = new NOF(asdu);
			element.next_element = new NOS(asdu);
			element.next_element.next_element = new LOS(asdu);
			element.next_element.next_element.next_element = new Segment(asdu, element.next_element.next_element.value);
			break;
		case F_SR_NA_1:
			element = new NOF(asdu);
			element.next_element = new NOS(asdu);
			element.next_element.next_element = new LOS(asdu);
			element.next_element.next_element.next_element = new SRQ(asdu);
			break;
		case M_BO_NA_1:
			element = new BSI(asdu);
			element.next_element = new QDS(asdu);
			break;
		case M_BO_TB_1:
			element = new BSI(asdu);
			element.next_element = new QDS(asdu);
			element.next_element.next_element = new CP56Time2a(asdu);
			break;
		case M_DP_NA_1:
			element = new DIQ(asdu);
			element.next_element = new QDS(asdu);
			break;
		case M_DP_TB_1:
			element = new DIQ(asdu);
			element.next_element = new QDS(asdu);
			element.next_element.next_element = new CP56Time2a(asdu);
			break;
		case M_EI_NA_1:
			element = new COI(asdu);
			break;
		case M_EP_TD_1:
			element = new SEP(asdu);
			element.next_element = new CP56Time2a(asdu);
			break;
		case M_EP_TE_1:
			element = new SPE(asdu);
			element.next_element = new QDP(asdu);
			element.next_element.next_element = new CP56Time2a(asdu);
			break;
		case M_EP_TF_1:
			element = new OCI(asdu);
			element.next_element = new QDP(asdu);
			element.next_element.next_element = new CP56Time2a(asdu);
			break;
		case M_IT_NA_1:
			element = new BCR(asdu);
			break;
		case M_IT_TB_1:
			element = new BCR(asdu);
			element.next_element = new CP56Time2a(asdu);
			break;
		case M_ME_NA_1:
			element = new NVA(asdu);
			element.next_element = new QDS(asdu);
			break;
		case M_ME_NB_1:
			element = new SVA(asdu);
			element.next_element = new QDS(asdu);
			break;
		case M_ME_NC_1:
			element = new R32IEEE_STD_754(asdu);
			element.next_element = new QDS(asdu);
			break;
		case M_ME_ND_1:
			element = new NVA(asdu);
			element.next_element = new QDS(asdu);
			break;
		case M_ME_TD_1:
			element = new NVA(asdu);
			element.next_element = new QDS(asdu);
			element.next_element.next_element = new CP56Time2a(asdu);
			break;
		case M_ME_TE_1:
			element = new SVA(asdu);
			element.next_element = new QDS(asdu);
			element.next_element.next_element = new CP56Time2a(asdu);
			break;
		case M_ME_TF_1:
			element = new R32IEEE_STD_754(asdu);
			element.next_element = new QDS(asdu);
			element.next_element.next_element = new CP56Time2a(asdu);
			break;
		case M_PS_NA_1:
			element = new SCD(asdu);
			element.next_element = new QDS(asdu);
			break;
		case M_SP_NA_1:
			element = new SIQ(asdu);
			element.next_element = new QDS(asdu);
			break;
		case M_SP_TB_1:
			element = new SIQ(asdu);
			element.next_element = new QDS(asdu);
			element.next_element.next_element = new CP56Time2a(asdu);
			break;
		case M_ST_NA_1:
			element = new VTI(asdu);
			element.next_element = new QDS(asdu);
			break;
		case M_ST_TB_1:
			element = new VTI(asdu);
			element.next_element = new QDS(asdu);
			element.next_element.next_element = new CP56Time2a(asdu);
			break;
		case P_AC_NA_1:
			element = new QPA(asdu);
			break;
		case P_ME_NA_1:
			element = new NVA(asdu);
			element.next_element = new QPM(asdu);
			break;
		case P_ME_NB_1:
			element = new SVA(asdu);
			element.next_element = new QPM(asdu);
			break;
		case P_ME_NC_1:
			element = new R32IEEE_STD_754(asdu);
			element.next_element = new QPM(asdu);
			break;
		}
		return element;
	}
}

/**
 * Single-point information with quality descriptor CP8
 * 
 * @author ar421 SPI: single-point information BS1 RES: reserve BS3 check
 *         documentation of <a href='#'>DIQ</a> for more information on the
 *         class
 */
class SIQ extends Information_Element {
	@Override
	public String toString() {
		return "SIQ";
	}

	int SPI, RES;
	boolean BL, SB, NT, IV;

	SIQ(ASDU asdu) throws NotBinaryException {
		Bit[] bits = asdu.getData()[ASDU.dc++].getBits();
		int c = 0;
		IV = bits[c++].toBoolean();
		NT = bits[c++].toBoolean();
		SB = bits[c++].toBoolean();
		BL = bits[c++].toBoolean();
		RES = Octet.getDecimal(new Bit[] { bits[c++], bits[c++], bits[c++] });
		SPI = Octet.getDecimal(new Bit[] { bits[c++] });
	}
}

/**
 * Double-point information with quality descriptor CP8
 * 
 * @author ar421 DPI: double-point information UI2 RES: reserve BS2 check
 *         documentation of <a href='#'>DIQ</a> for more information on the
 *         class
 */
class DIQ extends Information_Element {
	@Override
	public String toString() {
		return "DIQ";
	}

	int DPI, RES;
	boolean BL, SB, NT, IV;
	QDS QDS;

	DIQ(ASDU asdu) throws NotBinaryException {
		Bit[] bits = asdu.getData()[ASDU.dc++].getBits();
		int c = 0;
		IV = bits[c++].toBoolean();
		NT = bits[c++].toBoolean();
		SB = bits[c++].toBoolean();
		BL = bits[c++].toBoolean();
		RES = Octet.getDecimal(new Bit[] { bits[c++], bits[c++] });
		DPI = Octet.getDecimal(new Bit[] { bits[c++], bits[c++] });
	}
}

/**
 * Quality descriptor CP8
 * 
 * @author ar421 RES: reserve BS3 OV: overflow-state The value of the
 *         INFORMATION OBJECT is beyond a predefined range of value (mainly
 *         applicable to analog values). BL: blocked-state The value of the
 *         INFORMATION OBJECT is blocked for transmission; the value remains in
 *         the state that was acquired before it was blocked. Blocking and
 *         unblocking may be initiated e.g. by a local lock or a local automatic
 *         cause. SB: substituted-state The value of the INFORMATION OBJECT is
 *         provided by input of an operator (dispatcher) or by an automatic
 *         source. NT: non-topical-state A value is topical if the most recent
 *         update was successful. It is not topical if it was not updated
 *         successfully during a specified time interval or it is unavailable.
 *         IV: invalidity-state A value is valid if it was correctly acquired.
 *         After the acquisition function recognizes abnormal conditions of the
 *         information source (missing or non-operating updating devices) the
 *         value is then marked invalid. The value of the INFORMATION OBJECT is
 *         not defined under this condition. The mark INVALID is used to
 *         indicate to the destination that the value may be incorrect and
 *         cannot be used.
 */
class QDS extends Information_Element {
	@Override
	public String toString() {
		return "QDS";
	}

	public QDS(ASDU asdu) throws NotBinaryException {
		Bit[] bits = asdu.getData()[ASDU.dc++].getBits();
		int c = 0;
		IV = bits[c++].toBoolean();
		NT = bits[c++].toBoolean();
		SB = bits[c++].toBoolean();
		BL = bits[c++].toBoolean();
		RES = Octet.getDecimal(new Bit[] { bits[c++], bits[c++], bits[c++] });
		OV = bits[c++].toBoolean();
	}

	int RES;
	boolean OV, BL, SB, NT, IV;
}

/**
 * Quality descriptor for events of protection equipment CP8
 * 
 * @author ar421 RES: reserve BS3 EI: elapsed time invalid Elapsed time is valid
 *         if it was correctly acquired. If the acquisition function recognizes
 *         abnormal conditions the elapsed time is marked invalid. The elapsed
 *         time of the INFORMATION OBJECT is not defined under this condition.
 *         The mark INVALID is used to indicate to the destination that the
 *         elapsed time may be incorrect and cannot be used. check documentation
 *         of <a href='#'>DIQ</a> for more information on the class
 */
class QDP extends Information_Element {
	@Override
	public String toString() {
		return "QDP";
	}

	public QDP(ASDU asdu) throws NotBinaryException {
		Octet data = asdu.getData()[ASDU.dc++];
		RES = Octet.getDecimal(new Bit[] { data.getBits()[0], data.getBits()[1], data.getBits()[2] });
		EI = data.getBits()[3].toBoolean();
		BL = data.getBits()[4].toBoolean();
		SB = data.getBits()[5].toBoolean();
		NT = data.getBits()[6].toBoolean();
		IV = data.getBits()[7].toBoolean();
	}

	int RES;
	boolean EI, BL, SB, NT, IV;
}

/**
 * Value with transient state indication, can be used for step position of
 * transformers or other step position information CP8
 * 
 * @author ar421 value I7
 */
class VTI extends Information_Element {
	@Override
	public String toString() {
		return "VTI";
	}

	public VTI(ASDU asdu) throws NotBinaryException {
		Octet data = asdu.getData()[ASDU.dc++];
		Transient = data.getBits()[0].toBoolean();
		int inter = Octet.getDecimal(new Bit[] { data.getBits()[2], data.getBits()[3], data.getBits()[4],
				data.getBits()[5], data.getBits()[6], data.getBits()[7] });
		switch (data.getBits()[1]) {
		case ZERO:
			value = inter;
		case ONE:
			value = inter - 64;
		}
	}

	boolean Transient;
}

/**
 * Normalized value
 * 
 * @author ar421 The resolution of measured values is not defined. If the
 *         resolution of the measured value is coarser than the unit of the LSB,
 *         then the least significant bits are set to zero. value F16
 */
class NVA extends Information_Element {
	@Override
	public String toString() {
		return "NVA";
	}

	public NVA(ASDU asdu) throws NotBinaryException {
		float before, after;
		Bit[] decimal = new Bit[15];
		Octet[] data = new Octet[] { asdu.getData()[ASDU.dc++], asdu.getData()[ASDU.dc++] };
		for (int i = 1; i < 8; i++) {
			decimal[i - 1] = data[1].getBits()[i];
			decimal[i + 6] = data[0].getBits()[i - 1];
		}
		decimal[14] = data[0].getBits()[7];
		after = (((2 ^ 15) + 1) - Octet.getDecimal(decimal)) ^ (-1);
		switch (data[2].getBits()[0]) {
		case ZERO:
			before = -1;
			value = before + after;
		case ONE:
			before = 1;
			value = before - after;
		}
	}
}

/**
 * Scaled value: value I16 This INFORMATION ELEMENT is defined for the
 * transmission of technological values such as current, voltage, power in their
 * physical units (e.g. A, kV, MW). Range and position of decimal point are
 * fixed parameters. Examples: Current: 103 A; transmitted value 103 Voltage:
 * 10,3 kV; transmitted value 103, decimal point 10^(-1)
 * 
 * @author ar421
 */
class SVA extends Information_Element {
	@Override
	public String toString() {
		return "SVA";
	}

	public SVA(ASDU asdu) throws NotBinaryException {
		Bit sign;
		Bit[] integer = new Bit[15];
		Octet[] data = new Octet[] { asdu.getData()[ASDU.dc++], asdu.getData()[ASDU.dc++] };
		sign = data[1].getBits()[0];
		for (int i = 1; i < 8; i++) {
			integer[i - 1] = data[1].getBits()[i];
			integer[i + 6] = data[0].getBits()[i - 1];
		}
		integer[14] = data[0].getBits()[7];
		switch (sign) {
		case ZERO:
			value = Octet.getDecimal(integer);
		case ONE:
			value = Octet.getDecimal(integer) - (2 ^ 15); // two's complement
		}
	}
}

/**
 * Short floating point number R32.23
 * 
 * @author ar421
 *
 */
class R32IEEE_STD_754 extends Information_Element {
	@Override
	public String toString() {
		return "R32IEEESTD754";
	}

	public R32IEEE_STD_754(ASDU asdu) throws NotBinaryException {
		Bit[] fraction, exponent;
		Bit sign;
		Octet[] data = new Octet[4];
		for (int i = 0; i < 4; i++) {
			data[i] = asdu.getData()[ASDU.dc++];
		}
		sign = data[3].getBits()[0];
		exponent = new Bit[8];
		fraction = new Bit[23];
		for (int i = 0; i < 7; i++) {
			exponent[i] = data[3].getBits()[i + 1];
			fraction[i] = data[2].getBits()[i + 1];
			fraction[i + 7] = data[1].getBits()[i];
			fraction[i + 15] = data[0].getBits()[i];
		}
		exponent[7] = data[2].getBits()[0];
		fraction[14] = data[1].getBits()[7];
		fraction[22] = data[0].getBits()[7];

		float sn = 0, exp, frc = 0;
		switch (sign) {
		case ZERO:
			sn = 1;
			break;
		case ONE:
			sn = -1;
			break;
		}
		exp = Octet.getDecimal(exponent) - 127;

		for (int i = 0; i < 23; i++) {
			switch (fraction[i]) {
			case ONE:
				frc += 2 ^ (-i);
				break;
			default:
				break;
			// NOP
			}
		}
		value = (float) sn * frc * exp;
	}

}

/**
 * Binary counter reading CP40
 * 
 * @author ar421 Counter_reading I32 Sequence_notation CP8{SQ,CY,CA,IV} SQ:
 *         sequence number UI5 CY: carry CA: counter was adjusted IV: invalid
 */
class BCR extends Information_Element {
	@Override
	public String toString() {
		return "BCR";
	}

	public BCR(ASDU asdu) throws NotBinaryException {
		Octet[] data = new Octet[5];
		for (int i = 0; i < 5; i++) {
			data[i] = asdu.getData()[ASDU.dc++];
		}
		Counter_reading = Octet.getDecimal(Octet.concat(Octet.concat(data[0].getBits(), data[1].getBits()),
				Octet.concat(data[2].getBits(),
						new Bit[] { data[3].getBits()[1], data[3].getBits()[2], data[3].getBits()[3],
								data[3].getBits()[4], data[3].getBits()[5], data[3].getBits()[6],
								data[3].getBits()[7] })));
		switch (data[3].getBits()[0]) {
		case ZERO:
			value = Counter_reading;
		case ONE:
			value = Counter_reading - (2 ^ 31);
		}
		IV = data[4].getBits()[0].toBoolean();
		CA = data[4].getBits()[1].toBoolean();
		CY = data[4].getBits()[2].toBoolean();
		Sequence_number = Octet.getDecimal(new Bit[] { data[4].getBits()[3], data[4].getBits()[4], data[4].getBits()[5],
				data[4].getBits()[6], data[4].getBits()[7] });
	}

	int Counter_reading, Sequence_number;
	boolean CY, CA, IV;
}

/**
 * Single event of protection equipment CP8
 * 
 * @author ar421 ES: event state UI2{indeterminate state, OFF, ON, indeterminate
 *         state} RES: reserve BS1 check the qualifier information elements for
 *         more information about this class
 */
class SEP extends Information_Element {
	@Override
	public String toString() {
		return "SEP";
	}

	public SEP(ASDU asdu) throws NotBinaryException {
		Octet data = asdu.getData()[ASDU.dc++];
		ES = Octet.getDecimal(new Bit[] { data.getBits()[0], data.getBits()[1] });
		RES = Octet.getDecimal(new Bit[] { data.getBits()[2] });
		EI = data.getBits()[3].toBoolean();
		BL = data.getBits()[4].toBoolean();
		SB = data.getBits()[5].toBoolean();
		NT = data.getBits()[6].toBoolean();
		IV = data.getBits()[7].toBoolean();
	}

	int ES, RES;
	boolean EI, BL, SB, NT, IV;
}

/**
 * Start events of protection equipment BS8
 * 
 * @author ar421 Start events are generated by the protection equipment when it
 *         detects faults. Start events are transient information. Commands to
 *         output circuits are generated by the protection equipment when it
 *         decides to trip the circuit-breaker. Output circuit information is
 *         transient information. The time between start and end of operation is
 *         the relay duration time. The time between start of operation and
 *         command to output circuit is the relay operating time. GS: general
 *         start of operation SL1: start of operation phase L1 SL2: start of
 *         operation phase L2 SL3: start of operation phase L3 SIE: start of
 *         operation IE (earth current) SRD: start of operation in reverse
 *         direction RES: reserve BS2
 */
class SPE extends Information_Element {
	@Override
	public String toString() {
		return "SPE";
	}

	public SPE(ASDU asdu) throws NotBinaryException {
		Octet data = asdu.getData()[ASDU.dc++];
		RES = Octet.getDecimal(new Bit[] { data.getBits()[0], data.getBits()[1] });
		SRD = data.getBits()[2].toBoolean();
		SIE = data.getBits()[3].toBoolean();
		SL3 = data.getBits()[4].toBoolean();
		SL2 = data.getBits()[5].toBoolean();
		SL1 = data.getBits()[6].toBoolean();
		GS = data.getBits()[7].toBoolean();
	}

	boolean SL1, SL2, SL3, SIE, SRD, GS;
	int RES;
}

/**
 * Output circuit information of protection equipment BS8
 * 
 * @author ar421 GC: general command to output circuit CL1: command to output
 *         circuit phase L1 CL2: command to output circuit phase L2 CL3: command
 *         to output circuit phase L3 RES: reserve BS1
 */
class OCI extends Information_Element {
	@Override
	public String toString() {
		return "OCI";
	}

	public OCI(ASDU asdu) throws NotBinaryException {
		Bit[] b = asdu.getData()[ASDU.dc++].getBits();
		RES = Octet.getDecimal(new Bit[] { b[0], b[1], b[2], b[3] });
		CL3 = b[4].toBoolean();
		CL2 = b[5].toBoolean();
		CL1 = b[6].toBoolean();
		GC = b[7].toBoolean();
	}

	boolean GC, CL1, CL2, CL3;
	int RES;
}

/**
 * Binary state information BS32
 * 
 * @author ar421 Value BS32
 */
class BSI extends Information_Element {
	@Override
	public String toString() {
		return "BSI";
	}

	String bitstring;

	public BSI(ASDU asdu) throws NotBinaryException {
		Octet[] data = new Octet[] { asdu.getData()[ASDU.dc++], asdu.getData()[ASDU.dc++], asdu.getData()[ASDU.dc++],
				asdu.getData()[ASDU.dc++] };
		value = Octet.getDecimal(Octet.concat(Octet.concat(data[0].getBits(), data[1].getBits()),
				Octet.concat(data[2].getBits(), data[3].getBits())));
		bitstring = data[0].toString() + data[1].toString() + data[2].toString() + data[3].toString();
	}
}

/**
 * Fixed test bit pattern, two octets
 * 
 * @author ar421 Value UI16
 */
class FBP extends Information_Element {
	@Override
	public String toString() {
		return "FBP";
	}
}

/**
 * Single command CP8
 * 
 * @author ar421 SCS: single command state BS1 BS1[2]<0> QOC: CP6{QU,S/E}
 */
class SCO extends Information_Element {
	@Override
	public String toString() {
		return "SCO";
	}

	public SCO(ASDU asdu) {
		Bit[] d = asdu.getData()[ASDU.dc].getBits();
		SCS = Octet.getDecimal(new Bit[] { d[6], d[7] });
	}

	int SCS;
}

/**
 * Double command CP8
 * 
 * @author ar421 DCS: double command state UI2 0:3: not permitted; 1: OFF; 2: ON
 *         QOC CP8
 */
class DCO extends Information_Element {
	@Override
	public String toString() {
		return "DCO";
	}

	public DCO(ASDU asdu) {
		Bit[] d = asdu.getData()[ASDU.dc].getBits();
		DCS = Octet.getDecimal(new Bit[] { d[6], d[7] });
	}

	int DCS;
}

/**
 * Regulating step command CP8
 * 
 * @author ar421 RCS: regulating step command state UI2 0:3: not permitted; 1:
 *         next step LOWER; 2: next step HIGHER QOC
 */
class RCO extends Information_Element {
	@Override
	public String toString() {
		return "RCO";
	}

	public RCO(ASDU asdu) {
		Bit[] d = asdu.getData()[ASDU.dc].getBits();
		RCS = Octet.getDecimal(new Bit[] { d[6], d[7] });
	}

	int RCS;
}

/**
 * Seven octet binary time (timetag) CP56
 * 
 * @author ar421 This binary time is defined in 6.8 of IEC 870-5-4. It is used
 *         for the clock synchronization command C_CS_NA_1 (see IEC 870-5-5).
 *         Day of week is not used in this companion standard and set to 0.
 *         milliseconds UI16 (59999) minutes UI6 (59) res1 UI1 invalid BS1 hours
 *         UI5 (23) res2 UI1 summer_time BS1 day_of_month UI5 (31) day_of_weak
 *         UI3 (7) months UI4 (11) res3 UI1 years UI7 (127) res4 UI1
 */
class CP56Time2a extends Information_Element {
	@Override
	public String toString() {
		return "CP56Time2a";
	}

	int milliseconds, minutes, res1, hours, res2, day_of_month, day_of_week, months, res3, years, res4;
	boolean IV, summer_time;

	public CP56Time2a(ASDU asdu) throws NotBinaryException {
		Octet[] data = new Octet[7];
		for (int i = 0; i < 7; i++) {
			data[i] = asdu.getData()[ASDU.dc++];
		}
		milliseconds = Octet.getDecimal(Octet.concat(data[0].getBits(), data[1].getBits()));
		minutes = Octet.getDecimal(new Bit[] { data[2].getBits()[0], data[2].getBits()[1], data[2].getBits()[2],
				data[2].getBits()[3], data[2].getBits()[4], data[2].getBits()[5] });
		res1 = Octet.getDecimal(new Bit[] { data[2].getBits()[6], data[2].getBits()[7] });
		hours = Octet.getDecimal(new Bit[] { data[3].getBits()[0], data[3].getBits()[1], data[3].getBits()[2],
				data[3].getBits()[3], data[3].getBits()[4] });
		res2 = Octet.getDecimal(new Bit[] { data[3].getBits()[5], data[3].getBits()[6] });
		day_of_month = Octet.getDecimal(new Bit[] { data[3].getBits()[7], data[4].getBits()[0], data[4].getBits()[1],
				data[4].getBits()[2], data[4].getBits()[3] });
		day_of_week = Octet.getDecimal(new Bit[] { data[4].getBits()[4], data[4].getBits()[5], data[4].getBits()[6] });
		months = Octet.getDecimal(
				new Bit[] { data[4].getBits()[7], data[5].getBits()[0], data[5].getBits()[1], data[5].getBits()[2] });
		res3 = Octet.getDecimal(new Bit[] { data[5].getBits()[3], data[5].getBits()[4] });
		years = Octet.getDecimal(
				new Bit[] { data[5].getBits()[5], data[5].getBits()[6], data[5].getBits()[7], data[6].getBits()[0],
						data[6].getBits()[1], data[6].getBits()[2], data[6].getBits()[3], data[6].getBits()[4] });
		res4 = Octet.getDecimal(new Bit[] { data[6].getBits()[5] });
		IV = data[6].getBits()[6].toBoolean();
		summer_time = data[6].getBits()[7].toBoolean();
	}
}

/**
 * Three octet binary time
 * 
 * @author ar421 This binary time is defined in clause 6.8 of IEC 870-5-4. It is
 *         used for the time tag of an INFORMATION OBJECT. The octets 4 up to 7
 *         are discarded. milliseconds UI16 (59999) minutes UI6 (59) res1 UI1
 *         invalid BS1
 */
class CP24Time2a extends Information_Element {
	@Override
	public String toString() {
		return "CP24Time2a";
	}

	int milliseconds, minutes, res1;
	boolean invalid;
}

/**
 * Two octet binary time
 * 
 * @author ar421 This is used for an elapsed time such as "Relay operating time"
 *         or "Relay duration time". milliseconds UI16
 */
class CP16Time2a extends Information_Element {
	@Override
	public String toString() {
		return "CP16Time2a";
	}

	int milliseconds;
}

/**
 * Cause of initialization CP8
 * 
 * @author ar421 cause UI7: 0: local power switch on 1: local manual reset 2:
 *         remote reset 3-31: compatible range 32-127: private range change BS1:
 *         0: initialization with unchanged local parameters 1: initialization
 *         after change of local parameters
 */
class COI extends Information_Element {
	@Override
	public String toString() {
		return "COI";
	}

	public COI(ASDU asdu) throws NotBinaryException {
		Bit[] d = asdu.getData()[ASDU.dc++].getBits();
		change = d[0].toBoolean();
		Bit[] integer = new Bit[7];
		for (int i = 1; i < 8; i++) {
			integer[i] = d[i];
		}
		cause = Octet.getDecimal(integer);
	}

	int cause;
	boolean change;
}

/**
 * Qualifier of interrogation UI8
 * 
 * @author ar421 value: 0: not used 1-19: compatible range 20: Station
 *         interrogation (global) 21: IoG1 (Interrogation of group 1) 22: IoG2
 *         23: IoG3 24: IoG4 25: IoG5 26: IoG6 27: IoG7 28: IoG8 29: IoG9 30:
 *         IoG10 31: IoG11 32: IoG12 33: IoG13 34: IoG14 35: IoG15 36: IoG16
 *         37-63: compatible range 64-255: private range
 */
class QOI extends Information_Element {
	@Override
	public String toString() {
		return "QOI";
	}

	public QOI(ASDU asdu) {
		value = Octet.getDecimal(asdu.getData()[ASDU.dc++].getBits());
	}
}

/**
 * Qualifier of counter interrogation command CP8
 * 
 * @author ar421 RQT: request UI6: 0: no counter requested 1: request counter
 *         group 1 2: request counter group 2 3: request counter group 3 4:
 *         request counter group 4 5: general request counter 6-31: compatible
 *         range 32-63: private range; FRZ: freeze UI2: 0: no freeze or reset 1:
 *         counter freeze without reset 2: counter freeze with reset 3: counter
 *         reset
 */
class QCC extends Information_Element {
	@Override
	public String toString() {
		return "QCC";
	}

	public QCC(ASDU asdu) {
		Bit[] d = asdu.getData()[ASDU.dc++].getBits();
		Bit[] dF = new Bit[2], dR = new Bit[6];
		for (int i = 0; i < 2; i++) {
			dF[i] = d[i];
			dR[i] = d[i + 2];
		}
		for (int i = 2; i < 6; i++) {
			dR[i] = d[i + 2];
		}
		RQT = Octet.getDecimal(dR);
		FRZ = Octet.getDecimal(dF);
	}

	int RQT, FRZ;
}

/**
 * Qualifier of parameter of measured values CP8
 * 
 * @author ar421 Threshold value is the minimum change of value required to
 *         cause a new transmission of a measured value. Limit for transmission
 *         is the value which, if exceeded, causes the transmission of a
 *         measured value. Each kind of parameter has to be defined by an
 *         unambiguous INFORMATION OBJECT ADDRESS per system.
 * 
 *         KPA: kind of parameter UI6: 0: not used 1: threshold value 2:
 *         smoothing factor (filter time constant) 3: low limit for transmission
 *         of measured values 4: high limit for transmission of measured values
 *         5-32: compatible range 32-63: private range; LPC: local parameter
 *         change 0: no change; 1: change POP: parameter in operation 0: in
 *         operation; 1: not in operation
 */
class QPM extends Information_Element {
	@Override
	public String toString() {
		return "QPM";
	}

	public QPM(ASDU asdu) throws NotBinaryException {
		Bit[] d = asdu.getData()[ASDU.dc++].getBits();
		POP = d[0].toBoolean();
		LPC = d[1].toBoolean();
		Bit[] i = new Bit[6];
		for (int k = 0; k < 6; k++) {
			i[k] = d[k + 2];
		}
		KPA = Octet.getDecimal(i);
	}

	int KPA;
	boolean LPC, POP;
}

/**
 * Qualifier of parameter activation UI8
 * 
 * @author ar421 value UI8: 0: not used 1: act/deact of the previously loaded
 *         parameters (object address = 0) 2: act/deact of the parameter of the
 *         addressed object 3: act/deact of persistent cyclic of periodic
 *         transmission of the addressed object 4-127: compatible range 128-255:
 *         private range; Act/deact is defined in the CAUSE OF TRANSMISSION
 */
class QPA extends Information_Element {
	@Override
	public String toString() {
		return "QPA";
	}

	public QPA(ASDU asdu) {
		value = Octet.getDecimal(asdu.getData()[ASDU.dc++].getBits());
	}
}

/**
 * Qualifier of command CP6 (not even a single octet)
 * 
 * @author ar421 QU: qualifier UI5: 0: no additional definition 1: short pulse
 *         2: long duration pulse, duration determined by a system 3: persistent
 *         output 4-8: compatible range 9-15: reserved for the selection of
 *         other predefined functions 16-31: private range; SE (S/E): BS1: 0:
 *         execute; 1: select
 */
class QOC extends Information_Element {
	@Override
	public String toString() {
		return "QOC";
	}

	public QOC(ASDU asdu) throws NotBinaryException {
		Bit[] d = asdu.getData()[ASDU.dc++].getBits();
		SE = d[0].toBoolean();
		QU = Octet.getDecimal(new Bit[] { d[1], d[2], d[3], d[4], d[5] });
	}

	int QU;
	boolean SE;
}

/**
 * Qualifier of reset process command UI8
 * 
 * @author ar421 value UI8: 0: not used 1: general reset of process 2: reset of
 *         pending information with time tag of the vent buffer 3-127:
 *         compatible range 128-255: private range
 */
class QRP extends Information_Element {
	@Override
	public String toString() {
		return "QRP";
	}

	public QRP(ASDU asdu) {
		value = Octet.getDecimal(asdu.getData()[ASDU.dc++].getBits());
	}
}

/**
 * File ready qualifier CP8
 * 
 * @author ar421 value UI7: 0: default 1-63: compatible range 64-127: private
 *         range; CON: confirmation of select, request, deactivate or delete
 *         BS1: 0: positive; 1: negative
 */
class FRQ extends Information_Element {
	@Override
	public String toString() {
		return "FRQ";
	}

	public FRQ(ASDU asdu) throws NotBinaryException {
		Bit[] d = asdu.getData()[ASDU.dc++].getBits();

		CON = d[0].toBoolean();

		Bit[] b = new Bit[7];

		for (int i = 0; i < 7; i++) {
			b[i] = d[i + 1];
		}
	}

	boolean CON;
}

/**
 * Selection ready qualifier
 * 
 * @author ar421 value UI7: 0: default 1-63: compatible range 64-127: private
 *         range READY: section's readiness to load BS1: 0: ready; 1: not ready
 */
class SRQ extends Information_Element {
	@Override
	public String toString() {
		return "SRQ";
	}

	public SRQ(ASDU asdu) throws NotBinaryException {
		Bit[] data = asdu.getData()[ASDU.dc++].getBits();

		READY = data[0].toBoolean();

		data[0] = Bit.ZERO;
		value = Octet.getDecimal(data);
	}

	boolean READY;
}

/**
 * Select and call qualifier CP8
 * 
 * @author ar421 value UI4: 0: not used 1: select file 2: request file 3:
 *         deactivate file 4: delete file 5: select section 6: request section
 *         7: deactivate section 8-10: compatible range 11-15: private range;
 *         ERR: UI4: 0: default 1: requested memory space not available 2:
 *         checksum failed 3: unexpected communication service 4: unexpected
 *         name of file 5: unexpected name of section 6-10: compatible range
 *         11-15: private range
 */
class SCQ extends Information_Element {
	@Override
	public String toString() {
		return "SCQ";
	}

	public SCQ(ASDU asdu) {
		Bit[] err, d;
		err = new Bit[4];
		d = new Bit[4];

		for (int i = 0; i < 4; i++) {
			err[i] = asdu.getData()[ASDU.dc++].getBits()[i];
			d[i] = asdu.getData()[ASDU.dc++].getBits()[i + 4];
		}

		value = Octet.getDecimal(d);
		ERR = Octet.getDecimal(err);
	}

	int ERR;
}

/**
 * Last section or segment qualifier UI8
 * 
 * @author ar421 value UI8: 0: not used 1: file transfer without deactivation 2:
 *         file transfer with deactivation 3: section transfer without
 *         deactivation 4: section transfer with deactivation 5-127: compatible
 *         range 128-255: private range
 */
class LSQ extends Information_Element {
	@Override
	public String toString() {
		return "LSQ";
	}

	public LSQ(ASDU asdu) {
		value = Octet.getDecimal(asdu.getData()[ASDU.dc++].getBits());
	}
}

/**
 * Acknowledge file or section qualifier CP8
 * 
 * @author ar421 value UI4: 0: not used 1: positive acknowledge of file transfer
 *         2: negative acknowledge of file transfer 4: negative acknowledge of
 *         section transfer 5-10: compatible range 11-15: private range; ERR
 *         UI4: 0: default 1: requested memory space not available 2: checksum
 *         failed 3: unexpected communication service 4: unexpected name of file
 *         5: unexpected name of section 6-10: compatible range 11-15: private
 *         range
 */
class AFQ extends Information_Element {
	@Override
	public String toString() {
		return "AFQ";
	}

	public AFQ(ASDU asdu) {
		Bit[] b = asdu.getData()[ASDU.dc++].getBits();
		Bit[] d = new Bit[4];
		Bit[] error = new Bit[4];
		for (int i = 0; i < 4; i++) {
			error[i] = b[i];
			d[i] = b[i + 4];
		}
		ERR = Octet.getDecimal(error);
		value = Octet.getDecimal(d);
	}

	int ERR;
}

/**
 * Name of File UI16
 * 
 * @author ar421 value UI16: 0: not used 1-65535: name of file
 */
class NOF extends Information_Element {
	@Override
	public String toString() {
		return "NOF";
	}

	public NOF(ASDU asdu) {
		Octet[] o = new Octet[] { asdu.getData()[ASDU.dc++], asdu.getData()[ASDU.dc++] };
		Bit[] d = new Bit[16];
		for (int i = 0; i < 8; i++) {
			d[i] = o[0].getBits()[i];
			d[i + 8] = o[1].getBits()[i];
		}
		value = Octet.getDecimal(d);
	}
}

/**
 * Name of section UI8
 * 
 * @author ar421 value UI8: 0: not used 1-255: name of section
 */
class NOS extends Information_Element {
	@Override
	public String toString() {
		return "NOS";
	}

	public NOS(ASDU asdu) {
		Bit[] d = asdu.getData()[ASDU.dc++].getBits();
		value = Octet.getDecimal(d);
	}
}

/**
 * Length of file or section UI8
 * 
 * @author ar421 value UI24: 0: not used 1-16777215: number of octets of the
 *         complete file or section
 */
class LOF extends Information_Element {
	@Override
	public String toString() {
		return "LOF";
	}

	public LOF(ASDU asdu) {
		Bit[] d = new Bit[24];
		int i1, i2, i3;
		i1 = ASDU.dc++;
		i2 = ASDU.dc++;
		i3 = ASDU.dc++;
		for (int i = 0; i < 8; i++) {
			d[i] = asdu.getData()[i1].getBits()[i];
			d[i + 8] = asdu.getData()[i2].getBits()[i];
			d[i + 16] = asdu.getData()[i3].getBits()[i];
		}
		value = Octet.getDecimal(d);
	}
}

/**
 * Length of segment UI8
 * 
 * @author ar421 The maximum number of n ranges between 234 (maximum length of
 *         link field, DATA UNIT IDENTIFIER AND INFORMATION OBJECT ADDRESSes)
 *         and 240 (minimum length of link field, DATA UNIT IDENTIFIER AND
 *         INFORMATION OBJECT ADDRESSes). value UI8: 0: not used 1-255: number
 *         of octets in the segment
 */
class LOS extends Information_Element {
	@Override
	public String toString() {
		return "LOS";
	}

	public LOS(ASDU asdu) {
		value = Octet.getDecimal(asdu.getData()[ASDU.dc++].getBits());
	}
}

/**
 * Checksum UI8
 * 
 * @author ar421 value UI8: arithmetic sum of disregarding overflows (sum modulo
 *         256) over all octets of a section (when used in a last segment PDU)
 *         or of a complete file (when used in a last section PDU)
 */
class CHS extends Information_Element {
	@Override
	public String toString() {
		return "CHS";
	}

	public CHS(ASDU asdu) {
		value = Octet.getDecimal(asdu.getData()[ASDU.dc++].getBits());
	}
}

/**
 * Status of file CP8
 * 
 * @author ar421 STATUS: UI7: 0: default 1-15: compatible range 16-32: private
 *         range; RES1: reserve 1 BS1 FOR: BS1: 0: name defines file; 1: name
 *         defines subdirectory; FA: BS1: 0: file waits for transfer; 1:
 *         transfer of this file is active;
 * 
 */
class SOF extends Information_Element {
	@Override
	public String toString() {
		return "SOF";
	}

	boolean FA, FOR;
	int STATUS, RES1;

	public SOF(ASDU asdu) throws NotBinaryException {
		Bit[] d = asdu.getData()[ASDU.dc++].getBits();

		FA = d[0].toBoolean();
		FOR = d[1].toBoolean();
		RES1 = Octet.getDecimal(new Bit[] { d[2] });
		STATUS = Octet.getDecimal(new Bit[] { d[3], d[4], d[5], d[6], d[7] });
	}
}

/**
 * Qualifier of set-point command CP8
 * 
 * @author ar421 QL UI7: 0: default 1-63: compatible range 64-127: private
 *         range; SE (S/E) BS1: 0: execute; 1: select
 */
class QOS extends Information_Element {
	@Override
	public String toString() {
		return "QOS";
	}

	public QOS(ASDU asdu) throws NotBinaryException {
		Bit[] d = asdu.getData()[ASDU.dc++].getBits();
		SE = d[0].toBoolean();
		Bit[] integer = new Bit[7];
		for (int i = 1; i < 8; i++) {
			integer[i] = d[i];
		}
		QL = Octet.getDecimal(integer);
	}

	int QL;
	boolean SE;
}

/**
 * Status and status change detection CP32
 * 
 * @author ar421 ST BS16: status bit in bit position n: 0: OFF; 1: ON CD BS16:
 *         status change detection bit in bit position n+16: 0: no status change
 *         was detected since last reported 1: at least one status change was
 *         detected since last reported A change detect has occurred if the
 *         monitored status point has completed at least one transition cycle
 *         since the last reporting of this information. A transition cycle is a
 *         0-1-0 or a 1-0-1 sequence.
 */
class SCD extends Information_Element {
	@Override
	public String toString() {
		return "SCD";
	}

	public SCD(ASDU asdu) throws NotBinaryException {
		Octet[] data = new Octet[4];
		for (int i = 0; i < 4; i++) {
			data[i] = asdu.getData()[ASDU.dc++];
		}
		ST = new boolean[16];
		CD = new boolean[16];
		for (int i = 0; i < 8; i++) {
			ST[i] = data[1].getBits()[i].toBoolean();
			ST[i + 8] = data[2].getBits()[i].toBoolean();
			CD[i] = data[3].getBits()[i].toBoolean();
			CD[i + 8] = data[4].getBits()[i].toBoolean();
		}
	}

	boolean[] ST, CD;
}

class TSC extends Information_Element {
	@Override
	public String toString() {
		return "TSC";
	}

	TSC(ASDU asdu) {
		value = Octet
				.getDecimal(Octet.concat(asdu.getData()[ASDU.dc++].getBits(), asdu.getData()[ASDU.dc++].getBits()));
	}
}

class Segment extends Information_Element {

	@Override
	public String toString() {
		return "SEGMENT";
	}

	Octet[] data;

	public Segment(ASDU asdu, float value) {
		data = new Octet[(int) value];
		for (int i = 0; i < value; i++) {
			data[i] = asdu.getData()[ASDU.dc++];
		}
	}

}