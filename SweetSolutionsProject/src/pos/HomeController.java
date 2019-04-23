package pos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController implements Initializable {

	@FXML
	private Button start;
	
	@FXML
	private Button back;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	@FXML //goes back to the main page when back button pressed
	private void handleButtonActionB(ActionEvent event) {
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	@FXML //goes to the transaction page when logged in
	private void handleButtonAction(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(application.Main.class.getResource("/pos/TransactionView.fxml"));
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
}