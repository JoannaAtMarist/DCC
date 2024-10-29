/**
 * INTERFACE
 * This interface specifies four methods related to aspects of a 2D object that has a bounding box or size.  
 * The Boundable interface will be implemented by the Shape class.
 */
public interface Boundable {

	/**
	 * Defines the header of a method that returns the width of a 2D Boundable object.
	 * @return
	 */
	public double width();
	
	/**
	 * Defines the header of a method that returns the height of a 2D Boundable object.
	 * @return
	 */
	public double height();
	
	/**
	 * Defines the header of a method that returns the perimeter of a 2D Boundable object.
	 * @return
	 */
	public double perimeter();
	
	/**
	 * Defines the header of a method that returns the area of a 2D Boundable object.
	 * @return
	 */
	public double area();

}