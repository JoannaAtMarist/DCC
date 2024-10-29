//package reference;

/**
 * A functional interface defining a method to indicate if the exoplanet meets some criteria
 * @author Adam Divelbiss
 *
 */
public interface BoundablePredicate {

	/**
	 * Indicates if the Boundable object meets the programmed criteria
	 * @param exoplanet - a Boundable object to be evaluated
	 * @return - true if criteria are met otherwise false
	 */
	boolean isOK(Boundable boundable);
	
}
