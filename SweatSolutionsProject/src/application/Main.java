package application;

import inventory.*;
import recipeBook.*;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	@FXML
	private Button Tstart;
	@FXML
	private Button Rstart;
	@FXML
	private Button Ostart;
	@FXML
	private Button Istart;
	
	public static Inventory inventory;
	public static RecipeBook r;
	
	@FXML //opens the pos system
	private void handleButtonActionT(ActionEvent event) {	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/pos/HomeView.fxml"));
		try {
			loader.load();
		} catch (IOException ex) {
			System.out.println("ERROR");
		}
		Parent p  = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(p));
		stage.showAndWait();
	}
	
	@FXML //opens the recepie system
	private void handleButtonActionR(ActionEvent event) {	
		//CHANGE ("/pos/HomeView.fxml") TO YOUR MAIN PAGE TO LOAD
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/recipeBook/Ui.fxml"));
		try {
			loader.load();
		} catch (IOException ex) {
			System.out.println("ERROR");
		}
		Parent p  = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(p));
		stage.showAndWait();
	}
	
	@FXML //opens the customOrder system
	private void handleButtonActionO(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/customOrders/sweetSolutionsCustomOrders.fxml"));
		try {
			loader.load();
		} catch (IOException ex) {
			System.out.println("ERROR");
		}
		Parent p  = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(p));
		stage.showAndWait();
	}
	
	@FXML //opens inventory system
	private void handleButtonActionI(ActionEvent event) {	
		Inventory inv = new Inventory();
		inv.startUI();
	}
	
	@Override //starts the application
	public void start(Stage primaryStage) {
		inventory = new Inventory();
		r = new RecipeBook();
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(Main.class.getResource("/application/MainView.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
