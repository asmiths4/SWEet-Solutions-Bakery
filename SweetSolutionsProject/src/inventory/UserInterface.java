package inventory;
	
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class UserInterface extends Application {
	
	public static Validation rules;

	private ScrollPane stuff;
	private BorderPane root;
	private VBox viewItemPage;
	private VBox newItemPage;
	private VBox addItemPage;
	private VBox removeItemPage;
	
	private boolean tableDone = false;
	private VBox reportPage;
	private TableView<Item> table;
	private TableColumn<Item, String> name;
	private TableColumn<Item, Integer> amount;
	
	private VBox successPage;
	private VBox errorPage;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//error and success pages
			Label yay = new Label("Success!");
			successPage = new VBox(yay);
			successPage.setAlignment(Pos.CENTER);
			
			Label woop = new Label("Woop! Something went wrong!");
			Text error = new Text("");
			error.setId("error");
			error.setWrappingWidth(400);
			errorPage = new VBox(woop,error);
			errorPage.setAlignment(Pos.CENTER);
			
			//base page
			stuff = new ScrollPane();
			stuff.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
			stuff.setHbarPolicy(ScrollBarPolicy.NEVER);
			root = new BorderPane();
			stuff.setContent(root);
			Scene scene = new Scene(stuff,600,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());			
			primaryStage.setScene(scene);

			Label company = new Label("SWEet Solutions");
			company.setFont(new Font("Arial", 20));
			Label title = new Label("INVENTORY System");
			title.setFont(new Font("Arial", 30));
			VBox splash = new VBox(company, title);
			splash.setAlignment(Pos.CENTER);
			splash.setSpacing(50);
			
			//menu definitions
			MenuBar menu = new MenuBar();
			menu.prefWidthProperty().bind(primaryStage.widthProperty());
			Menu tasks_m = new Menu("Tasks");
			Menu help_m = new Menu("Help"); 
			MenuItem help = new MenuItem("Help is not coming..");
			help_m.getItems().addAll(help);
			MenuItem viewItem_m = new MenuItem("View Item");
			MenuItem newItem_m = new MenuItem("New Item");
			MenuItem addStorage_m = new MenuItem("Add to Storage");
			MenuItem removeStorage_m = new MenuItem("Remove from Storage");
			MenuItem lowIngrRep_m = new MenuItem("Low Ingredients Report");
			MenuItem lowGoodRep_m = new MenuItem("Low Baked Goods Report");
			tasks_m.getItems().addAll(viewItem_m, newItem_m, addStorage_m, removeStorage_m, lowIngrRep_m, lowGoodRep_m);
			
			//menu Events
			EventHandler<ActionEvent>viewItem_action = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					select_viewItem();
				}
			};
			viewItem_m.setOnAction(viewItem_action);
			
			EventHandler<ActionEvent>newItem_action = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					select_newItem();
				}
			};
			newItem_m.setOnAction(newItem_action);
			
			EventHandler<ActionEvent>addStorage_action = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					select_addStorage();
				}
			};
			addStorage_m.setOnAction(addStorage_action);
			
			EventHandler<ActionEvent>removeStorage_action = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					select_removeStorage();
				}
			};
			removeStorage_m.setOnAction(removeStorage_action);
			
			EventHandler<ActionEvent>lowIngrRep_action = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					select_reportIngr();
				}
			};
			lowIngrRep_m.setOnAction(lowIngrRep_action);
			
			EventHandler<ActionEvent>lowGoodRep_action = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					select_reportBakedGoods();
				}
			};
			lowGoodRep_m.setOnAction(lowGoodRep_action);

			
			menu.getMenus().addAll(tasks_m, help_m);
			root.setTop(menu);
			root.setCenter(splash);
			
			primaryStage.setTitle("SWEet Solutions : Inventory");
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//ViewItem was Clicked
	private void select_viewItem() {
		Label title = new Label("VIEW ITEM");
		title.setFont(new Font("Arial", 20));
		Label g1 = new Label("Select the type of the Item");
		ComboBox<String> isIngr = new ComboBox<String>();
		isIngr.setId("isingr");
		isIngr.getItems().addAll("Ingredient", "Baked Item");
		Label g2 = new Label("Enter the name of the Item");
		TextField name = new TextField();
		name.setId("name");
		name.setMaxWidth(200);
		
		Button search = new Button("Search");
		
		EventHandler<ActionEvent>submit_action = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				search_item();
			}
		};
		search.setOnAction(submit_action);

		Text result = new Text("");
		result.setId("result");
		result.setWrappingWidth(200);
		
		viewItemPage = new VBox(title, g1, isIngr, g2, name, search, result);
		viewItemPage.setSpacing(25);
		viewItemPage.setAlignment(Pos.BASELINE_LEFT);
		viewItemPage.setPadding(new Insets(50));
		root.setCenter(viewItemPage);
		
	}
	
	private void select_newItem() {
		Label title = new Label("NEW ITEM");
		title.setFont(new Font("Arial", 20));
		Label g1 = new Label("Select the type of the Item");
		ComboBox<String> isIngr = new ComboBox<String>();
		isIngr.setId("isingr");
		isIngr.getItems().addAll("Ingredient", "Baked Item");
		Label g2 = new Label("Enter the name of the item");
		TextField name = new TextField();
		name.setId("name");
		name.setMaxWidth(200);
		Label g3 = new Label("Enter units of measurement");
		TextField units = new TextField();
		units.setId("units");
		units.setMaxWidth(200);
		Label g4 = new Label("What is the amount considered low?");
		TextField low = new TextField();
		low.setId("low");
		low.setMaxWidth(200);
		Label g5 = new Label("Enter the quantity to be added");
		TextField q = new TextField();
		q.setId("q");
		q.setMaxWidth(200);
		Label g6 = new Label("Enter the price");
		TextField p = new TextField();
		p.setId("p");
		p.setMaxWidth(200);
		
		Button submit = new Button("Submit");
		EventHandler<ActionEvent>submit_action = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				new_item();
			}
		};
		submit.setOnAction(submit_action);
		
		newItemPage = new VBox(title, g1, isIngr, g2, name, g3, units, g4, low, g5, q, g6, p, submit);
		newItemPage.setSpacing(25);
		newItemPage.setAlignment(Pos.BASELINE_LEFT);
		newItemPage.setPadding(new Insets(50));
		root.setCenter(newItemPage);
	}
	
	private void select_addStorage() {
		Label title = new Label("ADD TO STORAGE");
		title.setFont(new Font("Arial", 20));
		Label g1 = new Label("Select the type of the Item");
		ComboBox<String> isIngr = new ComboBox<String>();
		isIngr.setId("ingr");
		isIngr.getItems().addAll("Ingredient", "Baked Item");
		Label g2 = new Label("Enter the name of the item");
		TextField name = new TextField();
		name.setId("name");
		name.setMaxWidth(200);
		Label g3 = new Label("Enter quantity to be added");
		TextField q = new TextField();
		q.setId("q");
		q.setMaxWidth(200);

		Button submit = new Button("Submit");
		EventHandler<ActionEvent>submit_action = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				add_item();
			}
		};
		submit.setOnAction(submit_action);
		
		addItemPage = new VBox(title, g1, isIngr, g2, name, g3, q, submit);
		addItemPage.setSpacing(25);
		addItemPage.setAlignment(Pos.BASELINE_LEFT);
		addItemPage.setPadding(new Insets(50));
		root.setCenter(addItemPage);
	}
	
	private void select_removeStorage() {
		Label title = new Label("REMOVE FROM STORAGE");
		title.setFont(new Font("Arial", 20));
		Label g1 = new Label("Select the type of the Item");
		ComboBox<String> isIngr = new ComboBox<String>();
		isIngr.setId("isingr");
		isIngr.getItems().addAll("Ingredient", "Baked Item");
		Label g2 = new Label("Enter the name of the item");
		TextField name = new TextField();
		name.setId("name");
		name.setMaxWidth(200);
		Label g3 = new Label("Enter quantity to be removed");
		TextField q = new TextField();
		q.setId("q");
		q.setMaxWidth(200);

		Button submit = new Button("Submit");
		EventHandler<ActionEvent>submit_action = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				remove_item();
			}
		};
		submit.setOnAction(submit_action);
		
		removeItemPage = new VBox(title, g1, isIngr, g2, name, g3, q, submit);
		removeItemPage.setSpacing(25);
		removeItemPage.setAlignment(Pos.BASELINE_LEFT);
		removeItemPage.setPadding(new Insets(50));
		root.setCenter(removeItemPage);
		
	}
	
	
	private void select_reportIngr() {
		Label title = new Label("Low Ingredients Report");
		title.setFont(new Font("Arial", 20));
		ArrayList<Item> data;
		
		if(!tableDone) {
			
			table = new TableView<Item>();
			table.setEditable(false);
		
			name = new TableColumn<Item, String>("Name");
			amount = new TableColumn<Item, Integer>("Amount");
				
			name.setPrefWidth(300);
			amount.setPrefWidth(150);
			table.setMaxWidth(452);
								
			//now adding columns
			PropertyValueFactory<Item,String> nameCVF = new PropertyValueFactory<Item,String>("Name");
			name.setCellValueFactory(nameCVF);
			
			PropertyValueFactory<Item,Integer> amountCVF = new PropertyValueFactory<Item,Integer>("Quantity");
			amount.setCellValueFactory(amountCVF);
			
			table.getColumns().addAll(name, amount);
			
			reportPage = new VBox(title, table);
			reportPage.setAlignment(Pos.CENTER);
			reportPage.setPadding(new Insets(25));
			reportPage.setSpacing(15);
			
		}
		else {
			table.getItems().clear();
		}	

		data = rules.runLowReportKitchen();
		ObservableList<Item> list = FXCollections.observableArrayList(data);

		table.setItems(list);			

		root.setCenter(reportPage);
	}
	
	private void select_reportBakedGoods() {
		Label title = new Label("Low Baked Goods Report");
		title.setFont(new Font("Arial", 20));
		ArrayList<Item> data;
		
		if(!tableDone) {
			
			table = new TableView<Item>();
			table.setEditable(false);
		
			name = new TableColumn<Item, String>("Name");
			amount = new TableColumn<Item, Integer>("Amount");
				
			name.setPrefWidth(300);
			amount.setPrefWidth(150);
			table.setMaxWidth(452);
								
			//now adding columns
			PropertyValueFactory<Item,String> nameCVF = new PropertyValueFactory<Item,String>("Name");
			name.setCellValueFactory(nameCVF);
			
			PropertyValueFactory<Item,Integer> amountCVF = new PropertyValueFactory<Item,Integer>("Quantity");
			amount.setCellValueFactory(amountCVF);
			
			table.getColumns().addAll(name, amount);
			
			reportPage = new VBox(title, table);
			reportPage.setAlignment(Pos.CENTER);
			reportPage.setPadding(new Insets(25));
			reportPage.setSpacing(15);
			
		}
		else {
			table.getItems().clear();
		}	

		data = rules.runLowReportSales();
		ObservableList<Item> list = FXCollections.observableArrayList(data);

		table.setItems(list);			

		root.setCenter(reportPage);

	}
	
	private void search_item() {
		Node n;
		String name="";
		Boolean isIngredient = null;
		String isingr;
		ComboBox box;
		Text result = new Text();
		TextField text;
		String itemData = "Sorry, unable to find item " + name + " in the database";
		ArrayList<Node> children = new ArrayList<Node>(viewItemPage.getChildren());
		try {
		for(int i = 0; i < children.size(); i++) {
			n = children.get(i);
			if(n.getId() != null) {
				if(n.getId().equals("isingr")){
					if(n instanceof ComboBox) {
						box = (ComboBox) n;
						isingr = (String) box.getValue();
						if(isingr.equals("Ingredient")) {
							isIngredient = new Boolean(true);
						}
						else {
							isIngredient = new Boolean(false);
						}
					}	
				}//end if isIngredient
				else if(n.getId().equals("name")) {
					text = (TextField) n;
					name = text.getText();
				}
				else if(n.getId().equals("result")) {
					result = (Text) n;
				}
			}//end if not null
				
		}
		
		if(! name.equals("") && isIngredient != null) {
			itemData = rules.getItemData(isIngredient, name.toLowerCase());
		}
		
		result.setText(itemData);
		}
		catch(NullPointerException e) {
			throwError("\n\nLooks like you forgot to fill out all parts of the form! Please try again :)");
		}
	}
	
	private void new_item() {
		Node n;
		String name="";
		Boolean isIngredient = null;
		String isingr = null;
		String units = null;
		int q = -1;
		boolean success = false;
		double p = -1;
		int low = -1;
		ComboBox box;
		Text result = new Text();
		TextField text;
		String itemData = "Sorry, unable to find item " + name + " in the database";
		try {
			ArrayList<Node> children = new ArrayList<Node>(newItemPage.getChildren());
		for(int i = 0; i < children.size(); i++) {
			n = children.get(i);
			if(n.getId() != null) {
				if(n.getId().equals("isingr")){
					if(n instanceof ComboBox) {
						box = (ComboBox) n;
						isingr = (String) box.getValue();
						if(isingr.equals("Ingredient")) {
							isIngredient = new Boolean(true);
						}
						else {
							isIngredient = new Boolean(false);
						}
					}	
				}//end if isIngredient
				else if(n.getId().equals("name")) {
					text = (TextField) n;
					name = text.getText();
				}
				else if(n.getId().equals("units")) {
					text = (TextField) n;
					units = text.getText();
				}
				else if(n.getId().equals("low")) {
					text = (TextField) n;
					low = Integer.parseInt(text.getText());
				}
				else if(n.getId().equals("q")) {
					text = (TextField) n;
					q = Integer.parseInt(text.getText());
				}
				else if(n.getId().equals("p")) {
					text = (TextField) n;
					p = Double.parseDouble(text.getText());
				}
			}//end if not null
				
		}
		
		if(! name.equals("") && isIngredient != null) {
			try {
				success = rules.newItem(isIngredient, name, units, low, q, p);
				if(!success) {
					throwError("\n\nUnable to add the item to the database. Please check your inputs. Numberic values must be positive numbers. The name must be unique - cannot add the same item twice.");
				}
				else {
					root.setCenter(successPage);
					return;
				}
			}
			catch(Exception x) {
				throwError("\n\nLooks like the data was incomplete or malformed! Please try again!");
				return;
			}
		}
		
		result.setText(itemData);
		}
		catch(NullPointerException e) {
			throwError("\n\nLooks like you forgot to fill out all parts of the form! Please try again :)");
		}
		catch(NumberFormatException y) {
			throwError("\n\nLooks like the numberic values weren't properly formatted! Please try again :)");
		}
		
	}
	
	private void throwError(String message) {
		ArrayList<Node> children = new ArrayList<Node>(errorPage.getChildren());
		Text result;
		Node n;
		for(int i = 0; i < children.size(); i++) {
			n = children.get(i);
			if(n.getId() != null && n.getId().equals("error")){
				result = (Text) n;
				result.setText(message);
				root.setCenter(errorPage);
			}
		}
	}
	
	
	private void add_item() {
		Node n;
		String name="";
		Boolean isIngredient = null;
		int success = -1;
		String isingr;
		ComboBox box;
		TextField text;
		int amount = -1;
		String itemData = "Sorry, unable to find item " + name + " in the database";
		try {
			ArrayList<Node> children = new ArrayList<Node>(addItemPage.getChildren());
			for(int i = 0; i < children.size(); i++) {
				n = children.get(i);
				if(n.getId() != null) {
					if(n.getId().equals("ingr")){
						box = (ComboBox) n;
						isingr = (String) box.getValue();
						if(isingr.equals("Ingredient")) {
							isIngredient = new Boolean(true);
						}
						else {
							isIngredient = new Boolean(false);
						}
				}//end if isIngredient
				else if(n.getId().equals("name")) {
					text = (TextField) n;
					name = text.getText();
				}
				else if(n.getId().equals("q")) {
					text = (TextField) n;
					amount = Integer.parseInt(text.getText());
				}
			}//end if not null				
		}
		try {
			if(isIngredient) {
				success = rules.putIngredient(name, amount);
			}
			else {
				success = rules.putBakedItem(name, amount);
			}
			if(success != -1) {
				root.setCenter(successPage);
				return;
			}
			else {
				throwError("\n\nUnable to add this item to storage. Please make sure the item already exists in the system and the amount to be added is not a negative number");
				return;
			}
		}
		catch(Exception e) {
			throwError("\n\nLooks like the data you provided was malformed! Please try again.");
			return;
		}

		}//end big try
		catch(NullPointerException e) {
			throwError("\n\nLooks like you forgot to fill out all parts of the form! Please try again :)");
		}
		catch(NumberFormatException y) {
			throwError("\n\nLooks like the numberic values weren't properly formatted! Please try again :)");
		}
	}
	
	private void remove_item() {
		Node n;
		String name="";
		Boolean isIngredient = null;
		boolean success = false;
		String isingr;
		ComboBox box;
		TextField text;
		int amount = -1;
		String itemData = "Sorry, unable to find item " + name + " in the database";
		try {
			ArrayList<Node> children = new ArrayList<Node>(removeItemPage.getChildren());
			for(int i = 0; i < children.size(); i++) {
			n = children.get(i);
			if(n.getId() != null) {
				if(n.getId().equals("isingr")){
					if(n instanceof ComboBox) {
						box = (ComboBox) n;
						isingr = (String) box.getValue();
						if(isingr.equals("Ingredient")) {
							isIngredient = new Boolean(true);
						}
						else {
							isIngredient = new Boolean(false);
						}
					}	
				}//end if isIngredient
				else if(n.getId().equals("name")) {
					text = (TextField) n;
					name = text.getText();
				}
				else if(n.getId().equals("q")) {
					text = (TextField) n;
					amount = Integer.parseInt(text.getText());
				}
			}//end if not null
				
		}
		
		if(! name.equals("") && isIngredient != null) {
			try {
				if(isIngredient) {
					success = rules.withdrawIngredient(name, amount);
				}
				else {
					success = rules.withdrawBakedItem(name, amount);
				}
				if(success) {
					root.setCenter(successPage);
					return;
				}
				else {
					throwError("\n\nUnable to remove this item from storage. Please make sure the amount to be removed is not a negative number, and that there is enough of the item in storage.");
					return;
				}
			}
			catch(Exception e) {
				throwError("\n\nLooks like the data you provided was malformed! Please try again.");
				return;
			}
		}
		
		}
		catch(NullPointerException e) {
			throwError("\n\nLooks like you forgot to fill out all parts of the form! Please try again :)");
		}
		catch(NumberFormatException y) {
			throwError("\n\nLooks like the numberic values weren't properly formatted! Please try again :)");
		}
	}
	
/*	public static void main(String[] args) {
		launch(args);
	}*/
}
