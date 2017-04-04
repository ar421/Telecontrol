package iec_60870_5_104.apdu.asdu.dui;

import converters.Octet;

/**
 * Type Identifier: represents the 'type' of 'request' or 'service' the ASDU
 * requires or provides respectively
 * 
 * @author ar421
 *
 */
public enum Type_Identification {
	M_SP_NA_1, M_DP_NA_1, M_ST_NA_1, M_BO_NA_1, M_ME_NA_1, M_ME_NB_1, M_ME_NC_1, M_IT_NA_1, M_PS_NA_1, M_ME_ND_1, M_SP_TB_1, M_DP_TB_1, M_ST_TB_1, M_BO_TB_1, M_ME_TD_1, M_ME_TE_1, M_ME_TF_1, M_IT_TB_1, M_EP_TD_1, M_EP_TE_1, M_EP_TF_1, C_SC_NA_1, C_DC_NA_1, C_RC_NA_1, C_SE_NA_1, C_SE_NB_1, C_SE_NC_1, C_BO_NA_1, C_SC_TA_1, C_DC_TA_1, C_RC_TA_1, C_SE_TA_1, /**/C_SE_TB_1, C_SE_TC_1, C_BO_TA_1, M_EI_NA_1, C_IC_NA_1, C_CI_NA_1, C_RD_NA_1, C_CS_NA_1, C_RP_NA_1, C_TS_TA_1, P_ME_NA_1, P_ME_NB_1, P_ME_NC_1, P_AC_NA_1, F_FR_NA_1, F_SR_NA_1, F_SC_NA_1, F_LS_NA_1, F_AF_NA_1, F_SG_NA_1, F_DR_TA_1, F_SC_NB_1;
	@Override
	public String toString() {
		return "" + Octet.getDecimal(o.getBits()) + "";
	}
Octet o;
	/**
	 * returns an object or instance of this class corresponding to the Octet
	 * taken.
	 * 
	 * @param octet
	 * @return
	 */
	static Type_Identification determine(Octet octet) {
		Type_Identification type = null;

		switch (Octet.getDecimal(octet.getBits())) {
		case 1:
			type = M_SP_NA_1;
			break;
		case 3:
			type = M_DP_NA_1;
			break;
		case 5:
			type = M_ST_NA_1;
			break;
		case 7:
			type = M_BO_NA_1;
			break;
		case 9:
			type = M_ME_NA_1;
			break;
		case 11:
			type = M_ME_NB_1;
			break;
		case 13:
			type = M_ME_NC_1;
			break;
		case 15:
			type = M_IT_NA_1;
			break;
		case 20:
			type = M_PS_NA_1;
			break;
		case 21:
			type = M_ME_ND_1;
			break;
		case 30:
			type = M_SP_TB_1;
			break;
		case 31:
			type = M_DP_TB_1;
			break;
		case 32:
			type = M_ST_TB_1;
			break;
		case 33:
			type = M_BO_TB_1;
			break;
		case 34:
			type = M_ME_TD_1;
			break;
		case 35:
			type = M_ME_TE_1;
			break;
		case 36:
			type = M_ME_TF_1;
			break;
		case 37:
			type = M_IT_TB_1;
			break;
		case 38:
			type = M_EP_TD_1;
			break;
		case 39:
			type = M_EP_TE_1;
			break;
		case 40:
			type = M_EP_TF_1;
			break;
		case 45:
			type = C_SC_NA_1;
			break;
		case 46:
			type = C_DC_NA_1;
			break;
		case 47:
			type = C_RC_NA_1;
			break;
		case 48:
			type = C_SE_NA_1;
			break;
		case 49:
			type = C_SE_NB_1;
			break;
		case 50:
			type = C_SE_NC_1;
			break;
		case 51:
			type = C_BO_NA_1;
			break;
		case 58:
			type = C_SC_TA_1;
			break;
		case 59:
			type = C_DC_TA_1;
			break;
		case 60:
			type = C_RC_TA_1;
			break;
		case 61:
			type = C_SE_TA_1;
			break;
		case 62:
			type = C_SE_TB_1;
			break;
		case 63:
			type = C_SE_TC_1;
			break;
		case 64:
			type = C_BO_TA_1;
			break;
		case 70:
			type = M_EI_NA_1;
			break;
		case 100:
			type = C_IC_NA_1;
			break;
		case 101:
			type = C_CI_NA_1;
			break;
		case 102:
			type = C_RD_NA_1;
			break;
		case 103:
			type = C_CS_NA_1;
			break;
		case 105:
			type = C_RP_NA_1;
			break;
		case 107:
			type = C_TS_TA_1;
			break;
		case 110:
			type = P_ME_NA_1;
			break;
		case 111:
			type = P_ME_NB_1;
			break;
		case 112:
			type = P_ME_NC_1;
			break;
		case 113:
			type = P_AC_NA_1;
			break;
		case 120:
			type = F_FR_NA_1;
			break;
		case 121:
			type = F_SR_NA_1;
			break;
		case 122:
			type = F_SC_NA_1;
			break;
		case 123:
			type = F_LS_NA_1;
			break;
		case 124:
			type = F_AF_NA_1;
			break;
		case 125:
			type = F_SG_NA_1;
			break;
		case 126:
			type = F_DR_TA_1;
			break;
		case 127:
			type = F_SC_NB_1;
			break;
		}
		type.o = octet;
		return type;
	}
}