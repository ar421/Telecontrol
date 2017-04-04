package iec_60870_5_104.apdu;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import exceptions.ElementTypeException;
import exceptions.InformationObjectAddressException;
import exceptions.NotBinaryException;
import iec_60870_5_104.apdu.asdu.object.Information_Element;
import iec_60870_5_104.apdu.asdu.object.Information_Object_Identifier;
/**
 * Information Object: contains the instructions or data the client wants the
 * server to execute. Consists of a set of information elements and an
 * information object identifier TODO implementation
 * 
 * @author ar421
 *
 */
public class Information_Object {
	@Override
	public String toString() {
		String string = "";
		
		for (Information_Element element : information_elements) {
			string += "ELEMENT(" + element.toString() + "),";
		}
		string += "END";
		return string;
	}
	Information_Object_Identifier information_object_identifier; // TODO fully implemented
	Set<Information_Element> information_elements;
	/**
	 * creates a new Information Object instance
	 * 
	 * @param ioi
	 * @param ie
	 * @param tt
	 * @throws ElementTypeException
	 * @throws InformationObjectAddressException
	 * @throws NotBinaryException 
	 */
	Information_Object(ASDU asdu) throws ElementTypeException, InformationObjectAddressException, NotBinaryException {
		information_object_identifier = new Information_Object_Identifier(asdu, 1);
		boolean am = asdu.getData_unit_identifier().getDut().getVsq().isAddressing_mode();
		information_elements = Collections.synchronizedSet(new HashSet<>());
		if (!am) {
			information_elements.add(Information_Element.createInfoElem(asdu));
		} else if (am) {
			while (ASDU.dc < asdu.getData().length) {
				information_elements.add(Information_Element.createInfoElem(asdu));
				ASDU.ic++;
			}
		}
	}
}