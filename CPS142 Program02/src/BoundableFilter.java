//package reference;

import java.util.ArrayList;

/**
 * A functional interface defining a method to filter an array of Shape objects
 * @author Adam Divelbiss
 *
 */
public interface BoundableFilter {

	/**
	 * Filters a given array of shape objects using the predicate
	 * @param array - An array of Shape objects to be filtered
	 * @param predicate - A predicate defining the filter conditions
	 * @return - the filtered array of Shape objects
	 */
	ArrayList<Boundable> filter(ArrayList<Boundable> array, BoundablePredicate predicate);
	
}
