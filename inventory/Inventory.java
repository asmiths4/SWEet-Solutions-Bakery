package inventory;

import java.io.File;
import java.util.ArrayList;

import com.sun.javafx.fxml.builder.JavaFXImageBuilder;

import javafx.stage.Stage;

public class Inventory{

	//this will be predefined differently outside of a demo
	private Database db;
	
	public Validation business;
		
	//public UserInterface ui = new UserInterface(business);
 
	public Inventory() {
		db  = new Database(new File("test_ingr"), new File("test_item"));
		business = new Validation(db);
		//inputs for testing purposes for the Recipe Book Sys
		business.newItem(true, "cream", "cups", 5, 40, 0.5);
		business.newItem(true, "butter", "cups", 100, 19, 0.5);
		business.newItem(true, "chocolate", "cups", 100, 50, 2.5);
		business.newItem(true, "flour", "cups", 100, 105, 2.5);
		business.newItem(true, "egg", "cups", 5, 50, 0.5);
		business.newItem(true, "strawberry", "cups", 5, 50, 0.5);
		business.newItem(true, "sugar", "cups", 10, 25, 1);
		business.newItem(true, "cookie", "cups", 15, 0, 10);
		business.newItem(true, "carrot", "cups", 15, 45, 0.25);
		
		//inputs for testing purposes for the POS Sys
		business.newItem(false, "cookie", "cups", 5, 10, 10);
        business.newItem(false, "cake", "cups", 5, 5, 15);
        business.newItem(false, "muffin", "cups", 5, 25, 5);
        business.newItem(false, "fudge", "cups", 5, 19, 7.5);
        business.newItem(false, "cupcake", "cups", 5, 15, 12);
        business.newItem(false, "Ice Cream", "cups", 5, 12, 10);
		
		UserInterface.rules = business;
	}
	
	public void startUI(){
		new Thread() {
			@Override
			public void run() {
				UserInterface.rules = business;
				javafx.application.Application.launch(UserInterface.class);
			}
		}.start();
	}
	
	//only for testing purposes
	public static void main(String [] args){
		Inventory inv = new Inventory();
		inv.startUI();
		
	}

}