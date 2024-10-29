
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/** 	Name: Joanna Picciano
 *		Course: CPS142 – Summer 2023
 *		Start date: 8/9/23
 *		Instructor: Adam Divelbiss
 *		Assignment: Program04
 *		Purpose: The program will analyze our Shape data file 
 *		to produce a width versus height histogram displaying 
 *		minimum width, maximum width, average width, minimum height, maximum height, average height, 
 *		and total number of shape records within a sequential set of width ranges.
 */
public class Main{

	public static final String SHAPE_FILE = "shapes_20230701.csv";


	public static void main(String[] args) {

		// Load the Shape data
		Set<Boundable> shapes = loadData();

		// Create a stream
		Stream<Boundable> stream = shapes.stream();	


		// PRINTOUT Header
		// The table must include 10 rows of data in 8 columns.
		// make each column 10 char?
		System.out.println("Welcome to the Shape Database 4.0!\n");
		System.out.println("Width/Height Histogram Analysis:\n\n");
		System.out.printf("%10s%10s%10s%10s%10s%10s%10s%10s\n",
				"", "Mininum", "Maximum", "Average", "Mininum", "Maximum", "Average", "");
		System.out.printf("%10s%10s%10s%10s%10s%10s%10s%10s\n",
				"Range", "Width", "Width", "Width", "Height", "Height", "Height", "Count");
		System.out.println("-----------------------------------------------------------------------------------");


		// LOOP		
		for (int range=0; range<10; range++) {

			final double widthMin = range * 10;
			final double widthMax = widthMin + 10;

			// Filter the shapes to those only within current width range
			// Save to a set for reuse
			Set<Boundable> widthRange = shapes.stream()
					.filter(shape -> shape.width()>= widthMin && shape.width() <= widthMax)
					.collect(Collectors.toSet());					

			// Get the number of items in the filtered set
			int shapesCount = widthRange.size();

			// Average Width
			// The average width of all shapes found in the current width range.
			double sumWidth = widthRange.stream()
					.reduce(0.0, (x, shape) -> x + shape.width(), Double::sum);
			double aveWidth = sumWidth / shapesCount;

			// Minimum Height
			// The minimum height of all shapes found in the current width range.
			stream = widthRange.stream();
			Boundable minHeight = Collections.min(widthRange, (x, y) -> {
				return Double.compare(x.height(), y.height());
			});

			// Maximum Height
			// The maximum height of all shapes found in the current width range.
			Boundable maxHeight = Collections.max(widthRange, (x, y) -> {
				return Double.compare(x.height(), y.height());
			});

			// Average Height
			// The average height of all shapes found in the current width range.
			double sumHeight = widthRange.stream()
					.reduce(0.0, (x, shape) -> x + shape.height(), Double::sum);
			double aveHeight = sumHeight / shapesCount;


			// PRINTOUT Data
			System.out.printf("%10d%,10.2f%,10.2f%,10.2f%,10.2f%,10.2f%,10.2f%,10d\n",
					range+1, widthMin, widthMax, aveWidth, 
					minHeight.height(), maxHeight.height(), aveHeight, shapesCount);

		} // end of For Loop

	} // End of main


	/**
	 * Load the shape data from our database
	 * @return the Set of Shape objects to use
	 */
	public static Set<Boundable> loadData() {

		Set<Boundable> data = new HashSet<>();

		// load the Shape data from the file
		// handle all exceptions including FileNotFoundException here using try-catch-finally blocks.

		try {
			File file = new File(SHAPE_FILE);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {

				// Read a line of code from the file.
				String line = scanner.nextLine().trim().toUpperCase();

				// convert the line variable into a Shape object using ShapeFactory.create
				// Use a try-catch block to catch the ShapeException.

				try {
					Shape shape = ShapeFactory.create(line);
					if (shape != null) {
						data.add(shape);
					}
				}
				catch (ShapeException e) {
					// I don't think we want the errors printed out in program 4
					//System.out.println(e.getMessage());
				}

			}
			scanner.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File cannot be found!");
		}

		return data;

	} //end of loadData

} //end of class
