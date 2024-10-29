/**
 * The BoundingBox class is a simple concrete class that 
 * holds width and height values of a two-dimensional "bounding box" for shapes. 
 * A bounding box is the smallest 2D rectangle that will completely hold another 2D object.
 */
public class BoundingBox {

	// FIELDS

	private double width;  // The "x-axis" or "width" value of the BoundingBox object
	private double height; // The "y-axis" or "height" value of the BoundingBox object

	// CONSTRUCTORS

	/**
	 * A no-arg constructor that sets the width and height fields to 0.0.
	 */
	public BoundingBox() {
		width = 0.0;
		height = 0.0;
	}

	/**
	 * A constructor that takes the width and height of the BoundingBox as double parameters.
	 * @param w
	 * @param h
	 */
	public BoundingBox(double w, double h) {
		width = w;
		height = h;
	}

	// METHODS

	/**
	 * Returns the value of the width field.
	 * @return
	 */
	public double getWidth() {
		return width;		
	}

	/**
	 * Returns the value of the height field
	 * @return
	 */
	public double getHeight() {
		return height;		
	}

}
