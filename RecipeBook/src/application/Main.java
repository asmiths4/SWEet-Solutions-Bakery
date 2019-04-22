package application;
	
import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Create the FXMLLoader 
	        FXMLLoader loader = new FXMLLoader();
	        // Path to the FXML File
	        String fxmlDocPath = "C:/Users/Amanda/eclipse-workspace/RecipeBook/src/application/Ui.fxml";
	        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
	         
	        // Create the Pane and all Details
	        AnchorPane root = (AnchorPane) loader.load(fxmlStream);
	         
	        // Create the Scene
	        Scene scene = new Scene(root);
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        // Set the Scene to the Stage
	        primaryStage.setScene(scene);
	        // Set the Title to the Stage
	        primaryStage.setTitle("Recipe Book");
	        // Display the Stage
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		/*RecipeBook r = new RecipeBook();
		try {
			r.checkRecipe("Chocolate");
		}
		catch(IOException e) {
			System.out.println("error");
		}*/ 
	}
}
