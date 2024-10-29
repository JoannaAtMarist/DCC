/**
 * ABSTRACT CLASS
 * The Shape class represents an “undifferentiated” or “unspecified” 2D Shape.  
 * It is an abstract class that partially implements the Boundable interface, implements Comparable
 */
public abstract class Shape implements Boundable, Comparable<Shape>{

	// FIELDS

	private ShapeType type; // The type of the current shape set to one of the ShapeType enumeration constants.
	private BoundingBox boundingBox; // An instance of the BoundingBox class that will represent the 2D “bounding box” of the shape.

	// CONSTRUCTORS

	/**
	 * Takes a ShapeType object as an argument. 
	 * Initializes type and boundingBox.
	 * @param type
	 */
	public Shape(ShapeType type) {
		this.type = type;
		boundingBox = new BoundingBox();
	}

	/**
	 * Takes a ShapeType object and a BoundingBox object as arguments.
	 * Initializes type and boundingBox.
	 * @param type
	 * @param box
	 */
	public Shape(ShapeType type, BoundingBox box) {
		this.type = type;
		this.boundingBox = box;
	}

	// METHODS

	/**
	 * Returns the value of the boundingBox field.
	 * @return
	 */
	public BoundingBox getBoundingBox() {
		return boundingBox;		
	}

	/**
	 * Returns the value of the type field.
	 * @return
	 */
	public ShapeType getType() {
		return type;		
	}

	/**
	 * Returns the value of the boundingBox field’s getWidth() method.
	 * @return
	 */
	public double width() {
		return boundingBox.getWidth();		
	}

	/**
	 * Returns the value of the boundingBox field’s getHeight() method.
	 * @return
	 */
	public double height() {
		return boundingBox.getHeight();		
	}

	/**
	 * This method will return a well-formatted String object to be used in the shape analysis report.
	 */
	@Override
	public String toString() {

		return String.format("%s\n %-13s %,10.5f\n %-13s %,10.5f\n %-13s %,10.5f\n %-13s %,10.5f\n", 
				this.type, "Width:", this.width(), "Height:", this.height(), "Area:", this.area(), "Perimeter:", this.perimeter());		

	}

	/**
	 * compareTo method required by Comparable<Shape> interface
	 */
	public int compareTo(Shape other) {
		if (Math.abs(this.area() - other.area()) <= 0.00001) {
			return 0;
		}
		return Double.compare(this.area(), other.area());
	}

}
