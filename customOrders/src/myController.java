//import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.IOException;

import application.Main;

//This class is the controller and handles getting the input from the view and giving the correct information to Main so the right page can be loaded.
	public class myController {

	    @FXML
	    private RadioButton mediumRadioButton;

	    @FXML
	    private Label sweetSolutionsLabel;

	    @FXML
	    private CheckBox strawberriesCheckBox;

	    @FXML
	    private RadioButton redVelvetRadioButton;

	    @FXML
	    private RadioButton creamCheeseRadioButton;

	    @FXML
	    private RadioButton carrotRadioButton;

	    @FXML
	    private Label extrasLabel;

	    @FXML
	    private RadioButton largeRadioButton;

	    @FXML
	    private RadioButton vanillaRadioButton;

	    @FXML
	    private Label colorLabel;

	    @FXML
	    private Label specialInstructionsLabel;

	    @FXML
	    private RadioButton ganacheRadioButton;

	    @FXML
	    private Button submitButton;

	    @FXML
	    private RadioButton chocolateRadioButton;

	    @FXML
	    private CheckBox grahamCrackersCheckBox;

	    @FXML
	    private RadioButton buttercreamRadioButton;

	    @FXML
	    private Label icingLabel;
	    
	    @FXML
	    private Button printResultsButton;

	    @FXML
	    private CheckBox whippedCreamCheckBox;

	    @FXML
	    private Label sizeLabel;

	    @FXML
	    private TextArea specialInstructionsTextArea;

	    @FXML
	    private CheckBox cocoaCheckBox;

	    @FXML
	    private Label baseItemLabel;

	    @FXML
	    private RadioButton smallRadioButton;
	    
	    @FXML
	    public Label reasonLabel;
	    
	    @FXML
	    public Label timeLabel;
	    
	    @FXML
	    public Label confirmSizeLabel;
	    
	    @FXML
	    public Label confirmIcingLabel;
	    
	    @FXML
	    public Label confirmToppingLabel;
	    
	    @FXML
	    public Label confirmInstructionsLabel;
	    
	    private static int orderNumber = 0;
	    private static String size;
	    private static String icing;
	    private static String topping;
	    private static String instructions;
	    public static String[] results = new String[2]; //The first holds the time til completion, the second holds a failure message if applicable.
	    public static boolean available;
	    public static String[] information;
	    
	    //Make the radio buttons only allow one choice at a time.
	    public void chocolateFunction() {
	       	vanillaRadioButton.setSelected(false);
	       	carrotRadioButton.setSelected(false);
	       	redVelvetRadioButton.setSelected(false);
	    
	    }
	    
	    public void vanillaFunction() {
	       	chocolateRadioButton.setSelected(false);
	       	carrotRadioButton.setSelected(false);
	       	redVelvetRadioButton.setSelected(false);
	    
	    }
	    
	    public void carrotFunction() {
	       	chocolateRadioButton.setSelected(false);
	       	vanillaRadioButton.setSelected(false);
	       	redVelvetRadioButton.setSelected(false);
	    
	    }
	    
	    public void redVelvetFunction() {
	       	chocolateRadioButton.setSelected(false);
	       	vanillaRadioButton.setSelected(false);
	       	carrotRadioButton.setSelected(false);
	    
	    }
	    
	    public void smallFunction() {
	       	mediumRadioButton.setSelected(false);
	       	largeRadioButton.setSelected(false);
	    
	    }
	    
	    public void mediumFunction() {
	       	smallRadioButton.setSelected(false);
	       	largeRadioButton.setSelected(false);
	    
	    }
	    
	    public void largeFunction() {
	       	mediumRadioButton.setSelected(false);
	       	smallRadioButton.setSelected(false);
	    
	    }
	    
	    public void buttercreamFunction() {
	       	creamCheeseRadioButton.setSelected(false);
	       	ganacheRadioButton.setSelected(false);
	    
	    }
	    
	    public void creamCheeseFunction() {
	       	buttercreamRadioButton.setSelected(false);
	       	ganacheRadioButton.setSelected(false);
	    
	    }
	    
	    public void ganacheFunction() {
	       	creamCheeseRadioButton.setSelected(false);
	       	buttercreamRadioButton.setSelected(false);
	    
	    }
	    
	    //Used You tube Video Javafx Tutorial 3 Changing Between Scenes
	    //used https://examples.javacodegeeks.com/desktop-java/javafx/scene/javafx-scene-builder-tutorial/#event_contr
	    @FXML
	    public void sendInformation() throws IOException {
	    	//Give the customer a new order number.
	    	myController.orderNumber++;
	    	
	    	//Turn input information over to the model to check with the recipe book.
	    	information = customOrdersModel.checkAvailability(chocolateRadioButton.isSelected(),vanillaRadioButton.isSelected(),carrotRadioButton.isSelected(),redVelvetRadioButton.isSelected(),smallRadioButton.isSelected(),mediumRadioButton.isSelected(),largeRadioButton.isSelected(),creamCheeseRadioButton.isSelected(),ganacheRadioButton.isSelected(),buttercreamRadioButton.isSelected(),grahamCrackersCheckBox.isSelected(),cocoaCheckBox.isSelected(),whippedCreamCheckBox.isSelected(),strawberriesCheckBox.isSelected(),specialInstructionsTextArea.getText());    	
	    	
	    	if(information[0] == "false") {
	    		available = false;
	    	}
	    	else {
	    		available = true;
	    	}
	    	
	    	//Load the confirmation page and prepare information to be printed.
	    	if(available) {
	    	  myController.results[0] = "3 Days";
	    	  myController.results[1] = information[1];
		      myController.size = information[2];
		      myController.icing = information[3];
		      myController.topping = information[4];
		      myController.instructions = information[5];
	    	  Main.changeToPage((Stage) submitButton.getParent().getScene().getWindow(),"..\\ConfirmationPage.fxml");
	    	}
	    	else {
		     myController.results[0] = "Not Available";
		     myController.results[1] = "We are unable to make your order at this time. \rPlease try a different order.";
		     myController.size = "";
		     myController.icing = "";
		     myController.topping = "";
		     myController.instructions = "";
		     Main.changeToPage((Stage) submitButton.getParent().getScene().getWindow(), "..\\ConfirmationPage.fxml");
	    	}
	    	return;
	    	
	    }
	    
	    //Print the confirmation information on the confirmation page
	    @FXML
	    public void printResults() {
	    	reasonLabel.setText(myController.results[1]);
	    	timeLabel.setText(myController.results[0]);
	    	confirmSizeLabel.setText(myController.size);
	    	confirmIcingLabel.setText(myController.icing);
	    	confirmToppingLabel.setText(myController.topping);
	    	confirmInstructionsLabel.setText(myController.instructions);
	    	return;
	    }
	    
	    //Go back to the order page.
	    @FXML
	    public void changeInformation() throws IOException{
	       	Stage st = (Stage) printResultsButton.getParent().getScene().getWindow();
            Main.changeToPage(st, "..\\sweetSolutionsCustomOrders.fxml");
	    	return;
	    }
	    


	}

