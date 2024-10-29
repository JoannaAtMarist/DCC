
/**
 * A Sorter class with a single method
 * Uses insertion sort method
 */
public class Sorter {

	/**
	 * Sorts an array of Comparable Shapes using insertion sort method
	 * @param array
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" }) // Ignore the warnings
	public static void sort(Comparable[] array) {

		// the first unsorted shape
		Comparable unsorted;
		// used to scan the array
		int scan;

		for (int index = 1; index < array.length; index++)
		{
			// Store the first element
			unsorted = array[index];

			// Start the scan at the first unsorted shape
			scan = index;

			// Move the first element in the still unsorted part
			// into its proper position within the sorted part.
			while (scan > 0 && array[scan-1].compareTo(unsorted) > 0) {

				array[scan] = array[scan-1];
				scan--;

			} //end of WHILE

			// Insert the unsorted shape in its proper position within the sorted part.
			array[scan] = unsorted;

		} //end of FOR

	} //end of method

} //end of class
