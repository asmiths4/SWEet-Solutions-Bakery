package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

import javafx.fxml.FXMLLoader;


//used https://stackoverflow.com/questions/21299038/root-hasnt-been-set-error-with-java-8-eclipse
//used https://stackoverflow.com/questions/20507591/javafx-location-is-required-even-though-it-is-in-the-same-package
//used https://examples.javacodegeeks.com/desktop-java/javafx/scene/javafx-scene-builder-tutorial/#event_contr
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException{
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(Main.class.getResource("..\\sweetSolutionsCustomOrders.fxml"));
			Scene scene = new Scene(root);
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

	public static void changeToPage(Stage st, String page) throws IOException {
		AnchorPane r = (AnchorPane) FXMLLoader.load(Main.class.getResource(page));
       	Scene s = new Scene(r);
       	st.setScene(s);
       	st.show();
		
	}
}
