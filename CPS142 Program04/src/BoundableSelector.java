//package reference;

/**
 * 
 * Interface that selects a Boundable object.
 * @author Adam Divelbiss
 *
 */
public interface BoundableSelector {

	/**
	 * Compares two Boundable objects and returns the one 
	 * that meets the desired criteria
	 * @param a - first Boundable object for comparison
	 * @param b - second Boundable object for comparison
	 * @return
	 */
	public Boundable select(Boundable a, Boundable b);
}
