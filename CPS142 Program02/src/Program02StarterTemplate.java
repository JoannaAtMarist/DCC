//package reference;  // Done - Remove this line or change to your own package if needed.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Done: Write your Project Documentation comment here.
/** 	Name: Joanna Picciano
 *		Course: CPS142 – Summer 2023
 *		Start date: 7/24/23
 *		Instructor: Adam Divelbiss
 *		Assignment: Program02
 *		Purpose: The program will implement a simple graphical user interface for the Shape database from Program 1 
 *		and will allow the user to display Shape information for all valid shape records 
 *		in the database and records for specific shape types.
 */
public class Program02StarterTemplate extends Application{

	public static final String SHAPE_FILE = "shapes_20230701.csv";

	// --------------------------
	// Application instance fields.
	// --------------------------
	// UI Related Fields
	private Stage stage;
	private BorderPane rootPane;	
	private MenuBar menuBar;

	// Data related fields
	private ArrayList<Boundable> shapes;

	private ArrayList<Boundable> shapesFiltered;


	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * start method for the JavaFX application
	 * @throws FileNotFoundException 
	 */
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {

		// Load the Shape data
		this.shapes = loadData();

		// Remember the primaryStage
		this.stage = primaryStage;

		// Create the Menu
		this.menuBar = new MenuBar();

		// Done: Use the buildFileMenu and buildModeMenu methods to populate the menuBar.
		menuBar.getMenus().add(this.buildFileMenu());
		menuBar.getMenus().add(this.buildModeMenu());

		// Create the rootPane and add the menuBar
		this.rootPane = new BorderPane();
		this.rootPane.setTop(menuBar);
		this.rootPane.setCenter(getShapeInfoScene());

		// Create the Scene
		Scene scene = new Scene(rootPane,900,450);

		// Get the scene
		primaryStage.setScene(scene);

		// set the title
		// Done: Set the title bar text
		primaryStage.setTitle("Shape Database 2.0");

		// set the css mode
		// Done: Load the preferred initial CSS file for light or dark mode
		scene.getStylesheets().add("light.css");

		// Show the primary stage
		primaryStage.show();

	}

	/**
	 * Builds and returns the file Menu
	 * @return
	 */
	private Menu buildFileMenu() {

		Menu fileMenu = new Menu("File");

		// Create the File Menu, with a single Exit menu item
		MenuItem exitItem = new MenuItem("Exit");
		fileMenu.getItems().add(exitItem);
		exitItem.setOnAction(event -> {
			// causes the application to quit
			stage.close();
		});

		return fileMenu;
	}


	/**
	 * Builds and returns the mode Menu
	 * @return
	 */
	private Menu buildModeMenu() {

		Menu modeMenu = new Menu("Mode");

		// Done: Add your code to create the complete Mode Menu

		RadioMenuItem lightModeMenu = new RadioMenuItem("Light");
		RadioMenuItem darkModeMenu = new RadioMenuItem("Dark");

		ToggleGroup toggleGroup = new ToggleGroup();
		lightModeMenu.setToggleGroup(toggleGroup);
		darkModeMenu.setToggleGroup(toggleGroup);

		modeMenu.getItems().add(lightModeMenu);
		modeMenu.getItems().add(darkModeMenu);

		// initialize the menu
		lightModeMenu.setSelected(true);

		// setup the event handlers
		lightModeMenu.setOnAction(event -> {
			this.stage.getScene().getStylesheets().clear();
			this.stage.getScene().getStylesheets().add("light.css");
		});

		darkModeMenu.setOnAction(event -> {
			this.stage.getScene().getStylesheets().clear();
			this.stage.getScene().getStylesheets().add("dark.css");
		});

		return modeMenu;
	}

	/**
	 * Build and return the main Shape information scene
	 * @return
	 */
	private Pane getShapeInfoScene() {

		//--------------------------------------------------------------------
		// Left-side Control Panel - Shape filter and statistics
		// Shape selector using ComboBox
		// Shape statistics using Labels
		//--------------------------------------------------------------------

		// Setup the filter selector as a ComboBox with options for
		// "All", "Circle", and "Rectangle".  Set the initial value to "All"

		// DONE: Replace "null" with your code and add code to 
		// initialize and configure the shapeFilterSelector

		// Title Label
		Label shapeFilterSelectorTitle = new Label("Select a Shape:");
		// Add the ComboBox controls
		ComboBox<String> shapeFilterSelector = new ComboBox<>();
		// Prevent editing
		shapeFilterSelector.setEditable(false);
		// Populate the ComboBox
		shapeFilterSelector.getItems().addAll("All", "Circle", "Rectangle");
		// Set the initial value to "All"
		shapeFilterSelector.setValue("All");


		// Filtered set statistics labels
		// DONE: Create and style 3 filtered data statistics labels using the local 
		// variable names given below. 
		// Replace "null" with your code and add appropriate styling as desired.
		Label filteredLabel = new Label("Statistics:\n");
		Label filteredTotalLabel = new Label();
		Label filteredLargestLabel = new Label();
		Label filteredSmallestLabel = new Label();


		// Setup the controls column
		// Done: Create the left-side controls column 
		// Use VBox, HBox, and Label controls.
		// Use appropriate styling and alignment methods.
		//
		// Incorporate the controls above including:
		//   shapeFilterSelector, 
		//   filteredTotalLabel,  
		//   filteredLargestLabel, 
		//   filteredSmallestLabel		
		//
		// Replace "null" with your code and add appropriate code for width and alignment as desired.
		VBox controlColumnLeft = new VBox (10, shapeFilterSelectorTitle, shapeFilterSelector, 
				filteredLabel,filteredTotalLabel, filteredLargestLabel, filteredSmallestLabel);
		controlColumnLeft.setPadding(new Insets(30));


		//--------------------------------------------------------------------
		// Center Control Panel
		// with Shape ListView selector
		//--------------------------------------------------------------------

		// Shape list view
		// Done: Create the ListView to display filtered Shape information.

		// Title Label
		Label shapeListViewTitle = new Label("Select a Shape");
		// Replace "null" with your code and add code to initialize and configure the shapeListView
		ListView<String> shapeListView = new ListView<>();
		// single selection mode
		shapeListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		shapeListView.setPrefSize(350, 300);


		// Done: Create the center control column including the ListView and an appropriate Label
		// Replace "null" with your code and add appropriate code for width and alignment as desired.
		VBox controlColumnCenter = new VBox (10, shapeListViewTitle, shapeListView);
		controlColumnCenter.setPadding(new Insets(30));


		//--------------------------------------------------------------------
		// Right-side Control Panel
		// Individual Shape information
		//--------------------------------------------------------------------

		// Shape Information column

		// Title Label
		Label shapeInfoLabelTitle = new Label("Selected Shape:");
		// DONE: Declare and initialize a Label called shapeInfoLabel
		// Replace "null" with your code.
		Label shapeInfoLabel = new Label();


		// Create the right-side column
		// DONE: Create the right-side control column including the shapeInfoLabel and other appropriate labels.
		// Replace "null" with your code and add appropriate code for width and alignment as desired.		
		VBox controlColumnRight = new VBox(10, shapeInfoLabelTitle, shapeInfoLabel);
		controlColumnRight.setPadding(new Insets(30));

		//--------------------------------------------------------------------
		// Setup the outside view wrappers
		//--------------------------------------------------------------------		
		// DONE: Update the HBox below to use the column VBox items above once they have
		// been completed
		// replace 
		HBox root = new HBox(
				20, 
				// replace the entire next line with just the variable, controlColumnLeft,
				controlColumnLeft,  
				// replace the entire next line with just the variable, controlColumnCenter,
				controlColumnCenter, 
				// replace the entire next line with just the variable, controlColumnRight
				controlColumnRight
				);


		//--------------------------------------------------------------------
		// Setup helpers
		//--------------------------------------------------------------------		
		// Select the Boundable (i.e., Shape) with the larger area
		// DONE: Replace "null" with a BoundableSelector using a Lambda expression or anonymous
		// inner class with select method that returns the Boundable (i.e., Shape) with the LARGER area
		BoundableSelector larger = (Boundable a,Boundable b) -> a.area() >= b.area() ? a : b;

		// Select the Boundable (i.e., Shape) with the smaller area
		// DONE: Replace "null" with a BoundableSelector using a Lambda expression or anonymous
		// inner class with select method that returns the Boundable (i.e., Shape) with the SMALLER area
		BoundableSelector smaller = (Boundable a,Boundable b) -> a.area() <= b.area() ? a : b;

		// Reduce a Boundable array using the given selector
		// arguments: ArrayList<Boundable>, BoundableSelector
		//
		// DONE: Implement a BoundableReducer using an anonymous inner class.
		// The "reduce" method of this class should implement the following 
		// pseudocode algorithm.
		// 
		// ALGORITHM reduce(array, selector)
		//
		//   set selected = first element of array
		//
		//   for each boundable in array
		//
		//     selected = selector.select(selected, boundable)
		//
		//   endFor
		//
		//   return selected
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

		// Predicate to check if the Boundable (i.e. Shape) has the user-selected shape type.
		//
		// DONE:  replace null with a lambda expression or anonymous inner class to implement a predicate that
		// returns true if the given Boundable, cast to a Shape has the same type as what the user selected using the
		// ComboBox<String> shapeFilterSelector.  
		//
		// Use the PSEUDOCODE algorithm below.  Convert this to proper Java
		// by properly declaring the local variables indicated using the pseudocode "set" command
		//
		// ALGORITHM isOK(boundable)
		//
		//   set selected = shapeFilterSelector.getValue().toLowerCase()
		//
		//   if selected equals "all" then return true
		//
		//   set shape = (Shape)boundable // cast the Boundable object to a Shape object.
		//
		//   set type = shape.getType().toString().toLowerCase()
		//
		//   returntype.equals(selected)
		//
		//

		BoundablePredicate predicate = new BoundablePredicate() {
			@Override
			public boolean isOK(Boundable boundable) {
				String selected = shapeFilterSelector.getValue().toLowerCase();
				if (selected.equals("all")) {
					return true;
				} 
				else {
					Shape shape = (Shape)boundable;
					String type = shape.getType().toString().toLowerCase();
					return type.equals(selected);
				}

			}};	


			// Filter the shapes given an array and a predicate
			// arguments: ArrayList<Boundable>, BoundablePredicate
			//
			// DONE: Implement a BoundableReducer using an anonymous inner class.
			// The "filter" method of this class should implement the following 
			// pseudocode algorithm.
			//
			// Use the PSEUDOCODE algorithm below.  Convert this to proper Java
			// by properly declaring the local variables indicated using the pseudocode "set" command
			//
			// ALGORITHM filter(array, predicate)
			//
			//   set filtered = new ArrayList<>()
			//
			//   for each boundable in array
			//
			//     if (predicate.isOK(boundable)
			//
			//        filtered.add(boundable)
			//
			//     endIf
			//
			//   endFor
			//
			//   return filtered
			//		
			BoundableFilter filter = new BoundableFilter() {
				@Override
				public ArrayList<Boundable> filter(ArrayList<Boundable> array, BoundablePredicate predicate) {
					ArrayList<Boundable> filtered = new ArrayList<>();
					for (Boundable boundable : array ) {
						if (predicate.isOK(boundable)){
							filtered.add(boundable);
						}
					}
					return filtered;
				}			
			};

			// Define an update handler that we can use to populate the application
			UpdateHandler handleShapeFilter = ()->{

				// Clear the right-side information
				// DONE: write a line of code to set the text of shapeInfoLabel to an empty string.
				shapeInfoLabel.setText("");

				// Get the filtered array and keep for future selection.
				shapesFiltered = filter.filter(shapes,predicate);

				// Find the largest Shape in the filtered array
				Boundable largest = reducer.reduce(shapesFiltered, larger);

				// Find the smallest Shape in the filtered array
				Boundable smallest = reducer.reduce(shapesFiltered, smaller);

				// Populate the statistics output
				// DONE: write a line of code to set the text of the filteredTotalLabel control to the number of items in the shapesFiltered array.
				filteredTotalLabel.setText("Total: " + Integer.toString(shapesFiltered.size()));

				// DONE: write a line of code to set the text of the filteredLargestLabel with the value of the largest area found
				filteredLargestLabel.setText("Largest: " + String.format("%.02f", largest.area()));

				// DONE: write a line of code to set the text of the filteredSmallestLabel with the value of the smallest area found
				filteredSmallestLabel.setText("Smallest: " + String.format("%.02f", smallest.area()));

				// populate the list view
				// 1. clear the current selection
				// DONE: Write a line of code to clear the current selection of the shapeListView control
				shapeListView.getSelectionModel().clearSelection();

				// 2. Clear the current list
				// DONE: Write a line of code to remove all items from the shapeListview control
				shapeListView.getItems().clear();

				// 3. add items to the list view
				// DONE: use a for loop or enhanced for loop to add a String for each item in the shapesFiltered array to the shapeListView control
				// Each string should contain the type of the shape, the area of the shape, and the perimeter of the shape.
				// Use String.format to help you create the String object for display.
				// 
				// ALGORITHM
				//   for each boundable in shapesFiltered
				//
				//     set shape = (Shape)boundable // cast the Boundable to a shape object.
				//
				//     set displayString = a string containing the the shape type, shape area, and shape perimeter
				//
				//     add the displayString to the shapeListView items array
				//
				//   endFor
				//

				// write your code here.
				for (Boundable b : shapesFiltered) {
					Shape shape = (Shape)b;
					String displayString = String.format("%s, %.02f, %.02f", shape.getType(), shape.area(), shape.perimeter());
					shapeListView.getItems().add(displayString);			
				}			
			};		

			//--------------------------------------------------------------------
			// Setup control event handlers
			//--------------------------------------------------------------------		

			// Configure the shapeFilterSelector event handler
			// DONE: Write a setOnAction event handler for the shapeFilterSelector
			// The handler should simply call the handleShapeFilter update() method.
			// Use a lambda expression to implement your code.
			//		
			shapeFilterSelector.setOnAction(event ->
			{
				handleShapeFilter.update();
			});				

			// Setup the Shape ListView listener - this handles user selection of an Shape and displays
			// information for the satellite in the right-hand panel.
			//
			// DONE: your listener should to the following:
			//
			// 1. Get the index of the currently selected item in the shapeListView as a int local variable called selectedIndex
			//
			// 2. Get the selected Boundable instance from the shapesFiltered array using the selectedIndex of step 1.
			//
			// 3. Set the text of the shapeInfoLabel by calling the selected Boundable instances toString method.
			//
			// Use a lambda expression to implement your code.

			shapeListView.getSelectionModel().selectedItemProperty().addListener(event -> {
				// 1. Get the index
				int selectedIndex = shapeListView.getSelectionModel().getSelectedIndex();		
				// 2. Get the selected Boundable instance
				Boundable selectedBoundable = shapesFiltered.get(selectedIndex);
				// 3. Set the text of the shapeInfoLabel
				shapeInfoLabel.setText(selectedBoundable.toString());			

			}); 

			// initialize the display
			// DONE: Initialize the display at startup by calling
			handleShapeFilter.update();


			return root;
	} // getShapeInfoScene



	/**
	 * Load the shape data from our database
	 * @return the ArrayList of Shape objects to use
	 */
	public static ArrayList<Boundable> loadData() throws FileNotFoundException {

		ArrayList<Boundable> data = new ArrayList<>();

		//DONE: add your code  to load the Shape data from the file

		// 1: you must handle all exceptions including FileNotFoundException here using try-catch-finally blocks.
		//       you may use nested try-catch blocks if needed, your choice.

		// 2: use your loadData method from Program 1 as a starting point.

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
