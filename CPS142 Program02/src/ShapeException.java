/**
 * This class will implement our own custom Exception class 
 * that we can use if there are any errors related to handling Shape objects.
 */
@SuppressWarnings("serial")
public class ShapeException extends Exception{

	/**
	 * Calls the super constructor.
	 */
	public ShapeException() {
		super("Error: General Shape Exception");
	}

	/**
	 * Calls the super constructor with a message.
	 * @param message
	 */
	public ShapeException(String message) {
		super(message);
	}

}
