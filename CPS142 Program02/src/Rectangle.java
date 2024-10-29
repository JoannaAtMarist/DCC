/**
 * This is a concrete class that represents a two-dimensional Rectangle shape.  
 * It inherits directly from the Shape abstract class
 */
public class Rectangle extends Shape {

	// CONSTRUCTORS

	/**
	 * The no-arg constructor for Rectangle.
	 */
	public Rectangle() {
		super(ShapeType.RECTANGLE);
	}

	/**
	 * This constructor takes a ShapeType object and a BoundingBox object as arguments.
	 * @param type
	 * @param box
	 */
	public Rectangle(ShapeType type, BoundingBox box) {
		super(type, box);
	}

	// METHODS

	/**
	 * Returns the perimeter of the Rectangle object.
	 * @return
	 */
	public double perimeter() {
		return 2 * (this.width() + this.height());
	}

	/**
	 * Returns the area of the Rectangle object.
	 * @return
	 */
	public double area() {
		return this.width() * this.height();
	}

}
