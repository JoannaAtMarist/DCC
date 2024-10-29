
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/** 	Name: Joanna Picciano
 *		Course: CPS141 – Summer 2023
 *		Start date: 6/29/23
 *		Instructor: Adam Divelbiss
 *		Assignment: Program05
 *		Purpose: A savings calculator using JavaFX
 */
public class Main extends Application {

	// class constant
	public static final int COMPOUND_FREQUENCY = 12;

	// main method
	public static void main(String[] args) {

		// Launch the application.
		launch(args);

	} // end of main method


	/**
	 * Calculates and returns the future value of investment
	 * @param initialDeposit
	 * @param monthlyDeposit
	 * @param interestRateAnnual
	 * @param term
	 * @return futureValue
	 */
	private static double getFutureValue
	(double initialDeposit, double monthlyDeposit, double interestRateAnnual, double term) {	//start of getFutureValue method

		// Algorithm to computer future value
		// Copied from previous program and edited loop
		double interestRateAnnualActual = interestRateAnnual / 100.0;
		double rateFactor = 1.0 + (interestRateAnnualActual / COMPOUND_FREQUENCY);
		double futureValue = initialDeposit;
		for (int i=0; i<(term * COMPOUND_FREQUENCY); i++) {
			futureValue = (futureValue + monthlyDeposit) * rateFactor;
		} //end of for loop

		return futureValue;

	} //end of getFutureValue method

	/**
	 *
	 */
	@Override
	public void start(Stage primaryStage) throws Exception{ //start of start method

		//----------------------------------------------------------------------
		// Input Section
		//----------------------------------------------------------------------
		TextField initialDepositInput = new TextField(Double.toString(1000));
		VBox initialDepositGroup = new VBox(2,new Label("Initial Deposit: $"),initialDepositInput); initialDepositGroup.setPadding(new Insets(5));
		// Complete the code to create the monthlyDepositInput and monthlyDepositGroup based on example above
		TextField monthlyDepositInput  = new TextField(Double.toString(100));		
		VBox monthlyDepositGroup = new VBox(2,new Label("Monthly Deposit: $"),monthlyDepositInput); monthlyDepositGroup.setPadding(new Insets(5));
		// Complete the code to create the interestRateInput and interestRateGroup based on example above TextField interestRateInput;
		TextField interestRateInput  = new TextField(Double.toString(4));		
		VBox interestRateGroup = new VBox(2,new Label("Interest Rate (Annual): %"),interestRateInput); interestRateGroup.setPadding(new Insets(5));
		// Complete the code to create the termInput and termGroup based on example above
		TextField termInput  = new TextField(Double.toString(20));		
		VBox termGroup = new VBox(2,new Label("Term (Years)"),termInput); termGroup.setPadding(new Insets(5));	
		// Create the input VBox
		// Add monthlyDepositGroup, interestRateGroup, and termGroup to the VBox below
		VBox inputGroup = new VBox(8, initialDepositGroup, monthlyDepositGroup, interestRateGroup, termGroup);

		//----------------------------------------------------------------------
		// Output Section
		//----------------------------------------------------------------------
		Label futureValueOutputLabel = new Label();
		VBox futureValueGroup = new VBox(2, new Label("Future Value: $"), futureValueOutputLabel); futureValueGroup.setPadding(new Insets(5));
		// Complete the code to create the totalDepositOutputLabel and totalDepositOutputGroup
		Label totalDepositOutputLabel = new Label();
		VBox totalDepositOutputGroup = new VBox(2, new Label("Total Deposits: $"), totalDepositOutputLabel); totalDepositOutputGroup.setPadding(new Insets(5));
		// Complete the code to create the earnedInterestOutputLabel and earnedInterestOutputGroup
		Label earnedInterestOutputLabel = new Label();
		VBox earnedInterestOutputGroup = new VBox(2, new Label("Earned Interest: $"), earnedInterestOutputLabel); earnedInterestOutputGroup.setPadding(new Insets(5));
		// Create the output VBox
		// Add totalDepositOutputLabel, earnedInterestOutputLabel to the VBox below
		VBox outputGroup = new VBox(20, futureValueGroup, totalDepositOutputGroup, earnedInterestOutputGroup);

		//----------------------------------------------------------------------
		// Create the Input/Output group
		//----------------------------------------------------------------------
		// create an HBox and add inputGroup and outputGroup to it
		HBox inputOutputGroup = new HBox(8, inputGroup, outputGroup); 

		//----------------------------------------------------------------------
		// Create the solve button and its event handler
		//----------------------------------------------------------------------
		// Create the solveButton using a Button control.
		// Give the button an appropriate label. 
		Button solveButton = new Button("Solve");
		// Handle the Solve Button
		solveButton.setOnAction((event) -> {
			// Capture input from the user for the deposit amount
			// interest rate, and term of the savings plan
			double initialDeposit = Double.parseDouble(initialDepositInput.getText());
			// use Double.parseDouble to convert monthlyDeposit input to double
			double monthlyDeposit  = Double.parseDouble(monthlyDepositInput.getText());
			// use Double.parseDouble to convert interestRateInput input to double
			double rate = Double.parseDouble(interestRateInput.getText()); 
			// use Double.parseDouble to convert termInput input to double
			double term = Double.parseDouble(termInput.getText()); 		
			// Compute the future value, earned interest and first year interest
			double futureValue = getFutureValue(initialDeposit, monthlyDeposit, rate, term);
			double totalDeposits = initialDeposit + (monthlyDeposit * term * COMPOUND_FREQUENCY);
			double earnedInterest = futureValue - totalDeposits;
			// Post the results to the output labels
			futureValueOutputLabel.setText(String.format("%,.2f", futureValue)/*convert futureValue to an appropriate String */); 
			totalDepositOutputLabel.setText(String.format("%,.2f", totalDeposits)/*convert totalDeposits to an appropriate String */); 
			earnedInterestOutputLabel.setText(String.format("%,.2f", earnedInterest)/*convert earnedInterest to an appropriate String */);
		}); // end of event

		//----------------------------------------------------------------------
		// Tie everything together
		//----------------------------------------------------------------------
		// Root node of the scene graph
		// Add the scene to the stage.
		// Create a VBox and add the inputOutputGroup, and solveButton to it
		VBox root = new VBox(8, inputOutputGroup, solveButton); 
		// Set any padding or alignment required for the VBox
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(10));
		// Create the Scene
		// Create a Scene object, add the root VBox to it and initialize to 400 wide by 330 high. 
		Scene scene = new Scene(root, 400, 330);
		// Add the scene to the Stage  (i.e., primaryStage)
		primaryStage.setScene(scene);
		// Set the title of the Stage (i.e., primaryStage)
		primaryStage.setTitle("Savings Calculator");
		// Show the Stage by calling its .show() method
		primaryStage.show();

	} // end of start method

} // end of class
