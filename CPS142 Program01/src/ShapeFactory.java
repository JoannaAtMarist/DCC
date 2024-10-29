// package reference;


// Done: Write your own class header documentation comment
/**
 * This is a “helper” or “utility” class that has a single static method 
 * that converts a string into the corresponding Shape object.
 */
public class ShapeFactory {


	// Done: Write your own method header documentation comment
	/**
	 * This class method will be used in the Main program to help create the array of objects loaded from the shapes database file.
	 * @param string
	 * @return shape
	 * @throws ShapeException
	 */
	public static Shape create(String string) throws ShapeException {

		// Define the shape variable
		Shape shape = null;
		ShapeType shapeType = ShapeType.ABSTRACT;
		double width = -1;
		double height = -1;

		// Done: trim and split the string parameter into its tokens using comma separators.
		String[] tokens = string.trim().split(","); // replace null with your one line of code to trim and split 

		// Get the shape type
		// Done: FINISH THIS CODE
		// 1. Write a try-catch block that handles a general Exception object
		try {
			// 2. Inside the try block, set the shapeType local variable using ShapeType.valueOf(tokens[0].trim())
			shapeType = ShapeType.valueOf(tokens[0].trim());
		} 
		catch (Exception e){
			// 3. Inside the catch block, throw a ShapeException with an appropriate message for the error.
			throw new ShapeException("Unable to get shape type.");
		}

		// Get the width
		// Done: FINISH THIS CODE
		// 1. Write a try-catch block that handles a general Exception object
		try {
			// 2. Inside the try block, set the width local variable using Double.parseDouble(tokens[1].trim())
			width = Double.parseDouble(tokens[1].trim());
		} 
		catch (Exception e){
			// 3. Inside the catch block, throw a ShapeException with an appropriate message for the error.
			throw new ShapeException("Invalid width value.");
		}
		// 4. After the catch block, if the width is less than 0 throw a ShapeException with an appropriate message.
		if (width<0) {
			throw new ShapeException("Invalid width value < 0: " + width);
		}


		// Get the height
		// Done: FINISH THIS CODE
		// 1. Write a try-catch block that handles a general Exception object
		try {
			// 2. Inside the try block, set the height local variable using Double.parseDouble(tokens[2].trim())
			height = Double.parseDouble(tokens[2].trim());
		} 
		catch (Exception e){
			// 3. Inside the catch block, throw a ShapeException with an appropriate message for the error.
			throw new ShapeException("Invalid height value.");
		}	
		// 4. After the catch block, if the height is less than 0 throw a ShapeException with an appropriate message.
		if (height<0) {
			throw new ShapeException("Invalid height value < 0: " + height);
		}

		// Create the bounding box for the shape
		BoundingBox box = new BoundingBox(width,height);

		// Create the shape for the specified shape type
		switch (shapeType) {
		case CIRCLE: 
			// done: Write the code to create a shape using the Circle constructor taking the shapeType and box
			shape = new Circle(shapeType, box);
			break;
		case RECTANGLE: 
			// done: Write the code to create a shape using the Rectangle constructor taking the shapeType and box
			shape = new Rectangle(shapeType, box);
			break;
		default:
			break;
		}			

		return shape;
	}

}
