package inventory;

import java.util.HashMap;
import java.util.ArrayList;

public final class Validation{

	private Database db;

	protected Validation(Database database){
		db = database;
	}

	public boolean withdrawBakedItem(String name, int amount){
		return withdraw(name, amount, false);
	}

	public boolean withdrawIngredient(String name, int amount){
		return withdraw(name, amount, true);
	}

	private boolean withdraw(String name, int amount, boolean isIngredient){
		Item thing;
		if(amount < 0){
			//System.out.println("Cannot withdraw a negative amount from inventory! " + name + ", " + Integer.toString(amount));
			return false;
		}
		try{
			thing = db.getItem(isIngredient, name);
			//this is not in the database
			if(thing == null){
				return false;
			}

			if((thing.getQuantity() - amount) < 0){
				return false;
			}
			else{
				thing.update(true, amount);
			}
		}
		catch(IllegalArgumentException e){
			return false;
		}
		return true;	
	}

	public int putBakedItem(String name, int amount){
		return put(name, amount, false);		
	}

	public int putIngredient(String name, int amount){
		return put(name, amount, true);
	}

	private int put(String name, int amount, boolean isIngredient){
		Item thing;
		if(amount < 0){
			System.out.println("Cannot add a negative amount to inventory! " + name + ", " + Integer.toString(amount));
			return -1;
		}

		try{
			thing = db.getItem(isIngredient, name);
			if(thing == null){
				return -1;
			}
			return thing.update(false, amount);
		}
		catch(IllegalArgumentException e){
			return -1;
		}
	}

	public int getAmountIngredient(String name){
		return getA(name, true);
	}

	public int getAmountItem(String name){
		return getA(name, false);
	}

	private int getA(String name, boolean isIngredient){
		Item thing;
		try{
			thing = db.getItem(isIngredient, name);
			return thing.getQuantity();
		}
		catch(IllegalArgumentException e){
			return -1;
		}
	}

	//for baked goods only
	public double getItemCost(String name){
		Item thing;
		try{
			thing = db.getItem(false, name);
			return thing.getPrice();
		}
		catch(IllegalArgumentException e){
			return -1;
		}
	}
	protected String getItemData(boolean isIngredient, String name) {
		Item thing = db.getItem(isIngredient, name);
		String result;
		if(thing == null) {
			return "Item does not exist";
		}
		result = "Name:\t" + thing.NAME + "\nQuantity:\t" + Integer.toString(thing.getQuantity()) + "\nUnits:\t" + thing.UNITS;
		result += "\nPrice:\t" + Double.toString(thing.getPrice()) + "\n";
		return result;
	}

	protected ArrayList<Item> runLowReportSales(){
		return runLowReport(false);
	}

	protected ArrayList<Item> runLowReportKitchen(){
		return runLowReport(true);
	}

	private ArrayList<Item> runLowReport(boolean isIngredient){
		ArrayList<Item> data;
		Item thing;
		//HashMap<String, Integer> lowStuff = new HashMap<String, Integer>();
		ArrayList<Item> lowStuff = new ArrayList<Item>();
		if(isIngredient){
			data = db.getAllIngredients();
		}
		else{
			data = db.getAllBaked();
		}

		for(int i = 0; i < data.size(); i++){
			thing = data.get(i);
			if (thing.isLow()){
				lowStuff.add(thing);
			}
		}

		return lowStuff;

	}

	protected boolean newItem(boolean isIngredient, String name,  String unit, int low, int q, double p){
		if(name == null || unit == null || low < 0 || q < 0 || p < 0){
			return false;
		}

		db.newItem(isIngredient, name.toLowerCase(), unit.toLowerCase(), low, q, p);

		return true;
	}

}