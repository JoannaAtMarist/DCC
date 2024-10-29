
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** 	Name: Joanna Picciano
 *		Course: CPS142 – Summer 2023
 *		Start date: 8/1/23
 *		Instructor: Adam Divelbiss
 *		Assignment: Program03
 *		Purpose: The program will implement a simple graphical user interface for the Shape database from Program 1 
 *		and will allow the user to display Shape information for all valid shape records 
 *		in the database and records for specific shape types.
 *		Program 3 adds sorting and searching features.
 *
 *		I have added TODO's where new content is and removed DONE's from previous assignment.
 */
public class Main extends Application{

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
	 */
	@Override
	public void start(Stage primaryStage) {

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
		Scene scene = new Scene(rootPane,1000,450);

		// Get the scene
		primaryStage.setScene(scene);

		// set the title
		primaryStage.setTitle("Shape Database 2.0");

		// set the css mode
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


		// TODO: 
		// Complete the following for the new Sort Controls.
		// 1. Declare and initialize two RadioButton instances plus an appropriate Label instance for
		// use in the Left Control Column of the interface.  The first RadioButton should
		// correspond to sorting in ascending (smallest to largest) order, and the second should
		// correspond to sorting in descending (largest to smallest).  The Label should indicate the
		// purpose of these controls to the user.  The variable names I used were
		// sortNormalRadioButton and sortReverseRadioButton.
		// 2. Use a ToggleGroup to ensure only one option is selected at a time.

		// Sort Controls
		// Title Label
		Label sortTitle = new Label("Sort order: ");
		// Create the radio buttons
		RadioButton sortNormalRadioButton = new RadioButton("Smallest to Largest");
		RadioButton sortReverseRadioButton = new RadioButton("Largest to Smallest");
		// Create the ToggleGroup
		ToggleGroup sortGroup = new ToggleGroup();
		// Add the RadioButtons to the ToggleGroup
		sortNormalRadioButton.setToggleGroup(sortGroup);
		sortReverseRadioButton.setToggleGroup(sortGroup);


		// Filtered set statistics labels
		Label filteredLabel = new Label("Statistics:\n");
		Label filteredTotalLabel = new Label();
		Label filteredLargestLabel = new Label();
		Label filteredSmallestLabel = new Label();


		// Setup the controls column
		// Create Vboxs within Vboxes for neatness?
		VBox leftInnerTop = new VBox (10, shapeFilterSelectorTitle, shapeFilterSelector);
		// TODO: Add the three new controls to the Left Control Column container.
		VBox leftInnerMiddle = new VBox (10, sortTitle, sortNormalRadioButton, sortReverseRadioButton);
		VBox leftInnerBottom = new VBox (10, filteredLabel, filteredTotalLabel, filteredLargestLabel, filteredSmallestLabel);
		VBox controlColumnLeft = new VBox (30, leftInnerTop, leftInnerMiddle, leftInnerBottom);
		controlColumnLeft.setPadding(new Insets(30));
		controlColumnLeft.setAlignment(Pos.TOP_LEFT);


		//--------------------------------------------------------------------
		// Center Control Panel
		// with Shape ListView selector
		//--------------------------------------------------------------------

		// Shape list view
		// Title Label
		Label shapeListViewTitle = new Label("Select a Shape");
		// Replace "null" with your code and add code to initialize and configure the shapeListView
		ListView<String> shapeListView = new ListView<>();
		// single selection mode
		shapeListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		shapeListView.setPrefSize(350, 300);


		// Create the center control column including the ListView and an appropriate Label
		VBox controlColumnCenter = new VBox (10, shapeListViewTitle, shapeListView);
		controlColumnCenter.setPadding(new Insets(30));


		//--------------------------------------------------------------------
		// Right-side Control Panel
		// Individual Shape information
		//--------------------------------------------------------------------

		// Shape Information column

		// Title Label
		Label shapeInfoLabelTitle = new Label("Selected Shape:");
		// Declare and initialize a Label called shapeInfoLabel
		Label shapeInfoLabel = new Label();


		// TODO: Shape Search Controls
		// Label for the “Search by Area” text
		Label searchTitle = new Label("Search by Area");
		// TextField for the name search user input, called shapeSearchTextField 
		TextField shapeSearchTextField = new TextField();
		// Button to execute the search using the name entered by the user called shapeSearchButton.
		Button shapeSearchButton = new Button("Find!");
		// Label to display the search results called shapeSearchLabel.
		Label shapeSearchLabel = new Label();

		// Make an HBox for neatness
		HBox searchAreaBox = new HBox(3, shapeSearchTextField, shapeSearchButton);


		// Create the right-side column
		// Create the right-side control column including the shapeInfoLabel and other appropriate labels.
		// TODO: Add the four new controls in an appropriate location in the Right Control Column container.
		VBox controlColumnRight = new VBox(10, shapeInfoLabelTitle, shapeInfoLabel,
				searchTitle, searchAreaBox, shapeSearchLabel);
		controlColumnRight.setPadding(new Insets(30));

		//--------------------------------------------------------------------
		// Setup the outside view wrappers
		//--------------------------------------------------------------------		
		HBox root = new HBox(20, controlColumnLeft, controlColumnCenter, controlColumnRight);


		//--------------------------------------------------------------------
		// Setup helpers
		//--------------------------------------------------------------------		
		// Select the Boundable (i.e., Shape) with the larger area
		BoundableSelector larger = (Boundable a,Boundable b) -> a.area() >= b.area() ? a : b;

		// Select the Boundable (i.e., Shape) with the smaller area
		BoundableSelector smaller = (Boundable a,Boundable b) -> a.area() <= b.area() ? a : b;

		// Reduce a Boundable array using the given selector
		// arguments: ArrayList<Boundable>, BoundableSelector
		//
		// Implement a BoundableReducer using an anonymous inner class.
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

		// Predicate to check if the Boundable (i.e. Shape) has the user-selected shape type.
		//
		// a lambda expression or anonymous inner class to implement a predicate that
		// returns true if the given Boundable, cast to a Shape has the same type as what the user selected using the
		// ComboBox<String> shapeFilterSelector.
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
			// Implement a BoundableReducer using an anonymous inner class.
			// The "filter" method of this class should implement the following 
			// pseudocode algorithm.
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
				// set the text of shapeInfoLabel to an empty string.
				shapeInfoLabel.setText("");

				// ~ OLD CODE FROM PROGRAM 2:
				// Get the filtered array and keep for future selection.
				// shapesFiltered = filter.filter(shapes,predicate);
				// ~

				// TODO: Inside the handleShapeFilter Lambda expression, 
				// use the following code to filter and sort the array of shapes.  
				// This line of code wraps the original filter code inside a call to the sort method created above.
				//
				// Get the filtered array and keep for future selection.
				// NEW FOR PROGRAM 3 - use the sort method to sort the filtered values.
				shapesFiltered = sort(filter.filter(shapes,predicate),sortReverseRadioButton.isSelected());

				// Find the largest Shape in the filtered array
				Boundable largest = reducer.reduce(shapesFiltered, larger);

				// Find the smallest Shape in the filtered array
				Boundable smallest = reducer.reduce(shapesFiltered, smaller);

				// Populate the statistics output
				// set the text of the filteredTotalLabel control to the number of items in the shapesFiltered array.
				filteredTotalLabel.setText("Total: " + Integer.toString(shapesFiltered.size()));

				// set the text of the filteredLargestLabel with the value of the largest area found
				filteredLargestLabel.setText("Largest: " + String.format("%.05f", largest.area()));

				// set the text of the filteredSmallestLabel with the value of the smallest area found
				filteredSmallestLabel.setText("Smallest: " + String.format("%.05f", smallest.area()));

				// populate the list view
				// 1. clear the current selection
				// clear the current selection of the shapeListView control
				shapeListView.getSelectionModel().clearSelection();

				// 2. Clear the current list
				// remove all items from the shapeListview control
				shapeListView.getItems().clear();

				// 3. add items to the list view
				// use a for loop or enhanced for loop to add a String for each item in the shapesFiltered array to the shapeListView control
				// Each string should contain the type of the shape, the area of the shape, and the perimeter of the shape.
				// Use String.format to help you create the String object for display.

				for (Boundable b : shapesFiltered) {
					Shape shape = (Shape)b;
					String displayString = String.format("%s, %.05f, %.05f", shape.getType(), shape.area(), shape.perimeter());
					shapeListView.getItems().add(displayString);			
				}			

				// TODO:
				// The search TextField (1.b) and the search results Label (1.d) must be cleared (i.e.,
				// .setText(“”)) each time your filtered array of Exoplanet objects is updated.  Write the code
				// to clear these fields in your “handleShapeFilter” UpdateHandler.
				shapeSearchTextField.clear();
				shapeSearchLabel.setText("");

			}; // End of UPDATE HANDLER


			//--------------------------------------------------------------------
			// Setup control event handlers
			//--------------------------------------------------------------------		

			// Configure the shapeFilterSelector event handler
			// a setOnAction event handler for the shapeFilterSelector
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
			// your listener should to the following:
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

			// TODO: 
			// For the Sort Ascending RadioButton, set an event handler that simply calls the
			// handleShapeFilter.update() method.
			// For the Sort Descending RadioButton, set an event handler that simply calls the
			// handleShapeFilter.update() method. 
			sortNormalRadioButton.setOnAction(event -> {
				handleShapeFilter.update();
			});
			sortReverseRadioButton.setOnAction(event -> {
				handleShapeFilter.update();
			});

			// TODO:
			// Use the following code, or modify to match your variable names, to handle the search feature
			// event when the user presses the search button.  
			// This code will call the Sorter.sort method and Searcher.seach methods that you wrote above.  
			// Place this code after the other setOnAction handlers in your code in the getShapeInfoScene method.
			// NEW FOR PROGRAM 3 - add search button handler
			// search button handler
			shapeSearchButton.setOnAction(event -> {
				// 1. Get the area value to find.
				double areaToFind = Double.parseDouble(shapeSearchTextField.getText());
				// 2. Create an instance of a Comparable<Shape> with the given area
				Shape objectToFind = new Shape(ShapeType.ABSTRACT) {
					@Override
					public double perimeter() {
						// value doesn't matter since we're not searching for perimeter, return 0.
						return 0;
					}
					@Override
					public double area() {
						return areaToFind; // return the area we want to find
					}
				};
				// 3. Create a regular Java array of Shape objects the same size as shapesFiltered Shape[] searchArray = new Shape[this.shapesFiltered.size()];
				Shape[] searchArray = new Shape[this.shapesFiltered.size()];
				// 4. Copy all of the the records in shapesFiltered into the search array
				for (int i=0; i<searchArray.length; i++) {
					searchArray[i] = (Shape)this.shapesFiltered.get(i);
				}
				// 5. Sort the searchArray using the preferred method
				Sorter.sort(searchArray);
				// 6. Search for the value using the Searcher
				int found = Searcher.search(searchArray, objectToFind);
				// 7. if the found index is less than 0 then display an appropriate not found message
				if (found < 0) {
					shapeSearchLabel.setText("Not found");
				}
				// 8. Otherwise display the shape at the found index of searchArray
				else {
					shapeSearchLabel.setText(searchArray[found].toString());
				}
			});


			// initialize the display
			handleShapeFilter.update();

			return root;

	} // end of getShapeInfoScene



	/**
	 * Load the shape data from our database
	 * TODO: Updated to throw file exception internally
	 * @return the ArrayList of Shape objects to use
	 */
	public static ArrayList<Boundable> loadData() {

		ArrayList<Boundable> data = new ArrayList<>();

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
					System.out.println(e.getMessage());
				}

			}
			scanner.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File cannot be found!");
		}

		return data;

	} //end of loadData


	// TODO: add Sort private static method
	// NEW FOR PROGRAM 3 - Method for sorting and array of Boundable objects, forward or reverse
	private static ArrayList<Boundable> sort(ArrayList<Boundable> unsorted, boolean reverseOrder) {
		// Convert the ArrayList to a regular Java array
		Shape[] sorted = new Shape[unsorted.size()];
		// Copy all of the the items in the the array
		for (int i=0; i<sorted.length; i++) {
			sorted[i] = (Shape)unsorted.get(i);
		}
		// Sort the Java array using the preferred method.
		Sorter.sort(sorted);
		// Created the final result array
		ArrayList<Boundable> result = new ArrayList<Boundable>(sorted.length);
		if (reverseOrder) {
			// Copy elements in reverse order
			for (int i=sorted.length-1; i>=0; i--) {
				result.add(sorted[i]);
			}
		} else {
			// Copy elements in natural order
			for (int i=0; i<sorted.length; i++) {
				result.add(sorted[i]);
			}
		}
		return result;
	} //end of sort method

} //end of class
