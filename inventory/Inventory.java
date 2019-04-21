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
		UserInterface.rules = business;
	}
	
	public void startUI(){
		new Thread() {
			@Override
			public void run() {
				javafx.application.Application.launch(UserInterface.class);
			}
		}.start();
	}
	
	//only for testing purposes
	public static void main(String [] args){
		Inventory inv = new Inventory();
		UserInterface.rules = inv.business;
		inv.startUI();
		
	}

}