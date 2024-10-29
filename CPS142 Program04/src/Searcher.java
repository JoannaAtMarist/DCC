
/**
 * A Searcher helper class with a single public static method.
 * Uses an iterative BinarySearch algorithm.
 */
public class Searcher {

	/**
	 * Searches the array of Comparable shapes for a given shape.
	 * Returns -1 if not found or the index of the shape if found
	 * @param array
	 * @param toFind
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" }) // Ignore the warnings
	public static int search(Comparable[] array, Comparable toFind) {

		int first; // First array element
		int last; // Last array element
		int middle; // Mid point of search
		int position; // Position of search value
		boolean found; // Flag

		// Set the initial values.
		first = 0;
		last = array.length - 1;
		position = -1;
		found = false;

		// Search the array
		while (!found  && first <= last) {

			// calculate the middle index
			middle = (first + last) / 2;


			// if the middle is the value
			// what we want: if array[middle] equals the desired value
			// compareTo will return 0 if d1 and d2 are equal
			// to make the if statement true we want 0 == 0 
			if (array[middle].compareTo(toFind) == 0) {
				found = true;
				position = middle;
			}

			// search the lower half
			// what we want: else if array[middle] is greater than the desired value
			// compareTo will return a value less than 0 if d1<d2, or a value greater than 0 if d1>d2
			// to make the else if true we want 1 > 0
			else if (array[middle].compareTo(toFind) > 0) {
				last = middle - 1;
			}

			// search the upper half
			else {
				first = middle + 1;
			}

		} //end of loop

		// return the position
		return position;

	} // end of method

} // end of class
