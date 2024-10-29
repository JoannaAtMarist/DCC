import java.util.ArrayList;

/**
 * FUNCTIONAL INTERFACE
 * This is a functional interface that defines a single method to "reduce" an ArrayList of Boundable objects 
 * to a single Boundable object based on a BoundableSelector object passed as the second argument.
 */
public interface BoundableReducer {

	/**
	 * Defines the header of a method that returns the Boundable instances from the array 
	 * that meets the criterion defined by the given BoundableSelector.
	 * @param array
	 * @param selector
	 * @return
	 */
	public Boundable reduce(ArrayList<Boundable> array, BoundableSelector selector);

}
