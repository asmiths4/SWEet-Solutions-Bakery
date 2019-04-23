package pos;

import application.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TransactionController implements Initializable {

	@FXML
	private Button done;
	@FXML
	private Button add;
	@FXML
	private Button remove;
	@FXML
	private TextField id;
	@FXML
	private TextField amount;
	@FXML
	private TextField tax;
	@FXML
	private TextField total;
	@FXML
	private TextArea inv;
	@FXML
	private TextArea reciet;
	
	private Transaction t;
	
	private ArrayList<inventory.Item> item;
	
	@Override //initializes screens and data
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		t = new Transaction();
		this.total.setText("$0");
		this.tax.setText("$0");
		loadInv();
	}
	
	// gets the current information from the database and puts it on the screen
	private void loadInv() {
		item = Main.inventory.business.getAllBaked();
		String test = "ID, Name, Amount, Price\n";
		//formatting for string to load into the screen
		for(int i=0 ; i< item.size() ; i++) {
			test += (i+1) + " " + item.get(i).getName() +", " 
					+ item.get(i).getQuantity() + 
					", $"+ Main.inventory.business.getItemCost(item.get(i).getName()) +"\n";
		}
		this.inv.setText(test);
	}
	
	// gets the current information from the receipt and puts it on the screen
	private void loadRec() {
		String test = "ID, Name, Amount, Price\n";
		//formatting for string to load into the screen
		for(int i=0 ; i< t.items.size() ; i++) {
			test += (i+1) + " " + t.items.get(i).name +", " 
					+ t.items.get(i).amount + 
					", $"+ t.items.get(i).price +"\n";
		}
		this.reciet.setText(test);
	}
	
	@FXML // add items from the DB to the receipt and updates the DB and totals
	private void handleButtonActionAdd(ActionEvent event) {
		String i = "";
		String a = "";
		//getting user input
		i = this.id.getText();
		a = this.amount.getText();
		int id = 0;
		int am = 0;
		//input validation
		try {
			id = Integer.parseInt(i);
			am = Integer.parseInt(a);
		} catch(Exception e) {
			id = -1;
			am = -1;
		}
		//more input validation, if valid continue with the application
		if(id > 0 && id <= item.size() && am > 0 && am <= item.get(id-1).getQuantity()) {
			TItem x = new TItem();
			x.name = item.get(id-1).getName();
			x.amount = am;
			x.price = am * (Main.inventory.business.getItemCost(item.get(id-1).getName()));
			boolean check = false;
			int hold = 0;
			// looking to see if item already in the receipt, if so update
			for(int j = 0; j < t.items.size(); j++) {
				if(t.items.get(j).name.equals(x.name)) {
					check = true;
					hold = j;
				}
			}
			//update item in receipt
			if(check) {
				t.items.get(hold).amount+= x.amount;
				t.items.get(hold).price+= x.price;
			}
			else {
				t.items.add(x);
			}
			//updating totals, DB, and Reloading the Screen information
			t.total += x.price;
			t.tax = t.total *  .04;
			t.taxTotal = t.total + t.tax;
			this.total.setText("$"+t.taxTotal);
			this.tax.setText("$"+t.tax);
			Main.inventory.business.withdrawBakedItem(x.name, x.amount);
			loadInv();
			loadRec();
		}
		else {
			this.id.setText("Error");
			this.amount.setText("Error");
		}
	}

	@FXML // removes items from the receipt and updates the DB and totals
	private void handleButtonActionRemove(ActionEvent event) {
		String i = "";
		String a = "";
		//getting user input
		i = this.id.getText();
		a = this.amount.getText();
		int id = 0;
		int am = 0;
		//input validation
		try {
			id = Integer.parseInt(i);
			am = Integer.parseInt(a);
		} catch(Exception e) {
			id = -1;
			am = -1;
		}
		//more input validation, if valid continue with the application
		if(id > 0 && id <= t.items.size() && am > 0 && am <= t.items.get(id-1).amount) {
			//subtracting item from receipt, updating total and DB
			double p = (t.items.get(id-1).price/t.items.get(id-1).amount);
			t.items.get(id-1).price -= am * p;
			t.items.get(id-1).amount -= am;
			t.total -= am * p;
			t.tax = t.total *  .04;
			t.taxTotal = t.total + t.tax;
			this.total.setText("$"+t.taxTotal);
			this.tax.setText("$"+t.tax);
			Main.inventory.business.putBakedItem(t.items.get(id-1).name, am);
			//removing item with 0 amount
			if(t.items.get(id-1).amount == 0) {
				t.items.remove(id-1);
			}
			//reloading screen
			loadInv();
			loadRec();
		}
		else {
			this.id.setText("Error");
			this.amount.setText("Error");
		}
	}
	
	@FXML //when finished with transaction close the screen and  "save" the transaction data
	private void handleButtonActionDone(ActionEvent event) {
		//t.save
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
}