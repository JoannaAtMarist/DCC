/**
 * This is a concrete class that represents a two-dimensional Circle.  
 * It inherits directly from the Shape abstract class.
 */
public class Circle extends Shape{

	// CONSTRUCTORS

	/**
	 * The no-arg constructor for Circle.
	 */
	public Circle() {
		super(ShapeType.CIRCLE);
	}

	/**
	 * This constructor takes a ShapeType object and a BoundingBox object as arguments.
	 * @param type
	 * @param box
	 */
	public Circle(ShapeType type, BoundingBox box) {
		super(type, box);
	}

	// METHODS

	/**
	 * Returns the radius of the Circle object.
	 * @return
	 */
	public double radius() {
		return this.width() / 2;
	}

	/**
	 * Returns the perimeter of the Circle object.
	 * @return
	 */
	public double perimeter() {
		return 2 * Math.PI * this.radius();
	}

	/**
	 * Returns the area of the Circle object.
	 * @return
	 */
	public double area() {
		return Math.PI * Math.pow(this.radius(), 2);
	}

}

