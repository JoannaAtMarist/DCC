//package reference;

import java.util.ArrayList;

/**
 * Functional interface defining a single
 * method to "reduce" an array of Boundable objects 
 * to a single Boundable object.
 * Can be used as a way to select a single Boundable
 * from all elements of the array.
 * 
 * @author Adam Divelbiss
 *
 */
public interface BoundableReducer {
	/**
	 * 
	 * "Reduces" an ArrayList of Boundable objects to a single Boundable object.
	 * @param array - the ArrayList of Boundable objects to process
	 * @param selector - the selector to use during processing
	 * @return
	 */
	public Boundable reduce(ArrayList<Boundable> array, BoundableSelector selector);

}
