// package reference;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


//*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*
//                STOP AND READ                //
//*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*
// DO NOT START YOUR WORK IN THIS FILE
// THIS SHOULD BE THE ABSOLUTE LAST FILE YOU WORK ON.
// WRITE ALL OF THE OTHER CODE FIRST
// THEN COMPLETE THIS PART OF THE PROJECT.
//
// WORK ON THE FILES IN THIS ORDER
//  1. Boundable.java
//  2. ShapeType.java
//  3. BoundingBox.java
//  4. Shape.java
//  5. Rectangle.java
//  6. Circle.java
//  7. ShapeException.java
//  8. ShapeFactory.java
//  9. BoundableSelector.java
// 10. BoundableReducer.java
// 11. Main.java - this file.
//
// IF YOU WOULD LIKE TO TEST YOUR CLASSES IN A MAIN PRORAM
// CREATE A SEPARATE JAVA MAIN PROGRAM FILE AND WORK IN IT.
//
//*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*!*


//Done: Write your project documentation comment here.
/** 	Name: Joanna Picciano
 *		Course: CPS142 – Summer 2023
 *		Start date: 7/18/23
 *		Instructor: Adam Divelbiss
 *		Assignment: Program01
 *		Purpose: Generate a report on an analysis of 10,000 2-dimensional shapes 
 *      to display the shapes with the largest width, largest height, largest area, and largest perimeter.
 */
public class Main {

	public static final String SHAPE_FILE = "shapes_20230701.csv";

	public static void main(String[] args) throws FileNotFoundException {

		System.out.println("Shape Analyzer 1.0");
		System.out.println();		

		// Create an array of Boundable shape objects objects
		ArrayList<Boundable> boundables = loadData();

		// Done: Replace "null" with a BoundableSelector using a Lambda expression that returns
		// the Boundable (i.e., Shape) with the larger width
		BoundableSelector maxWidth = (Boundable a,Boundable b) -> a.width() >= b.width() ? a : b;

		// Done: Replace "null" with a BoundableSelector using a Lambda expression that returns
		// the Boundable (i.e., Shape) with the larger height
		BoundableSelector maxHeight = (Boundable a,Boundable b) -> a.height() >= b.height() ? a : b;

		// Done: Replace "null" with a BoundableSelector using a Lambda expression that returns
		// the Boundable (i.e., Shape) with the larger area
		BoundableSelector maxArea = (Boundable a,Boundable b) -> a.area() >= b.area() ? a : b;

		// Done: Replace "null" with a BoundableSelector using a Lambda expression that returns
		// the Boundable (i.e., Shape) with the larger perimeter
		BoundableSelector maxPerimeter = (Boundable a,Boundable b) -> a.perimeter() >= b.perimeter() ? a : b;

		// Done: Implement a BoundableReducer using an anonymous inner class.
		// The "reduce" method of this class should implement the following 
		// pseudocode algorithm.
		// 		
		BoundableReducer reducer = new BoundableReducer() { // replace null with your anonymous inner class
			@Override
			// ALGORITHM reduce(array, selector)
			public Boundable reduce(ArrayList<Boundable> array, BoundableSelector selector) {
				// let selected = first element of array
				Boundable selected = array.get(0);
				for (Boundable boundable : array ) { // for each boundable in array
					//   selected = selector.select(selected, boundable)					
					selected = selector.select(selected, boundable);
				} // endFor
				return selected; // return selected
			} 
		};

		// Done: Write your code to printout the number of objects in the boundables array;
		System.out.println("Number of valid shapes: " + boundables.size() + "\n\n");


		// USE MY CODE BELOW TO PRINT OUT THE REPORT
		// DO NOT CHANGES THESE 4 LINES OF CODE IN ANY WAY EVEN TO CORRECT SYNTAX ERRORS.
		// IF YOU HAVE A SYNTAX ERROR IN ONE OF THESE LINES IT IS BECAUSE OF SOMETHING ELSE.
		System.out.printf("Largest width:\n%s\n\n", reducer.reduce(boundables,maxWidth));
		System.out.printf("Largest height:\n%s\n\n", reducer.reduce(boundables,maxHeight));
		System.out.printf("Largest area:\n%s\n\n", reducer.reduce(boundables,maxArea));
		System.out.printf("Largest perimeter:\n%s\n\n", reducer.reduce(boundables,maxPerimeter));	
		// END: DO NOT CHANGE MY CODE



		// Done: Write your code to printout a friendly "finished" message
		System.out.println("All done!");

	} // end main method



	/**
	 * Load the shape data from our database
	 * @return the ArrayList of Shape objects to use
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Boundable> loadData() throws FileNotFoundException {

		ArrayList<Boundable> data = new ArrayList<>();

		File file = new File(SHAPE_FILE);
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {

			// Read a line of code from the file.
			String line = scanner.nextLine().trim().toUpperCase();

			// Done:  Complete the code to convert the line variable into a Shape object using ShapeFactory.create
			// 
			// Use a try-catch block to catch the ShapeException.
			// 
			// Inside the try block do the following:
			//  1. Use ShapeFactory.create to convert the line String to a Shape object. 
			//     Save the result in a local variable called shape
			//  2. If shape is NOT null, add it to the data ArrayList defined above.
			//
			// Inside the catch block for ShapeException
			//   Print the exception message to the console using System.out.print or similar method.
			//

			// write your code after this point
			try {
				Shape shape = ShapeFactory.create(line);
				if (shape != null) {
					data.add(shape);
				}
			}
			catch (ShapeException e) {
				System.out.println(e.getMessage());
			}
			// write your code before this point

		}
		scanner.close();

		return data;

	}
}
