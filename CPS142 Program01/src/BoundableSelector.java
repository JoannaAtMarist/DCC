/**
 * FUNCTIONAL INTERFACE
 * This is a functional interface that defines a single method to "select" one Boundable object or the other 
 * by returning the selected Boundable object.
 */
public interface BoundableSelector {

	/**
	 * Defines the header of a method that returns either “a” or “b” 
	 * based on some criterion defined in the class or Lambda expression that implements it.
	 * @param a
	 * @param b
	 * @return
	 */
	public Boundable select(Boundable a, Boundable b);

}
