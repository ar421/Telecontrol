package exceptions;
/**
 * thrown if APDU size exceeds 255
 * @author ar421
 *
 */
public class APDUSizeOutOfBoundsException extends StdException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2655501628718315969L;

	public APDUSizeOutOfBoundsException() {
		super();
	}
}
