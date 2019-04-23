package inventory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import inventory.Item;

public class Database{
	private File ingr_storage; //mock, to be implemented further
	private File item_storage; //mock, to be implemented further
	private HashMap<String, Item> items;
	private HashMap<String, Item> ingredients;

	protected Database(File ingred, File ite){
		//mock. in-depth file operations to be implemented

		ingr_storage = ingred;
		item_storage = ite;

		//mock. 
		//further reading from files may take place


		items = new HashMap<String, Item>();
		ingredients = new HashMap<String, Item>();

		//mock.
		//further reading of file into the lists (as a sort of cache) will be implemented
	}


	protected int remove(boolean isIngredient, String name, int amount){
		Item thing;
		int newAmount = 0;

		if(isIngredient){
			thing = ingredients.get(name);
		}
		else{
			thing = items.get(name);
		}

		if(thing == null){
			throw new IllegalArgumentException("Item " + name + " does not exist.");
		}
		newAmount = thing.update(true, amount);

		//incomplete implementation - [demo version]
		//	run database sync job updating the underlying file structure
		//	this will be done in background

		return newAmount;
	}


	protected int add(boolean isIngredient, String name, int amount){
		Item thing;
		int newAmount = 0;

		if(isIngredient){
			thing = ingredients.get(name);
		}
		else{
			thing = items.get(name);
		}
		if(thing == null){
			throw new IllegalArgumentException("Item " + name + " does not exist.");
		}

		newAmount = thing.update(false, amount);

		//incomplete implementation - [demo version]
		//	run database sync job updating the underlying file structure
		//	this will be done in background

		return newAmount;
	}


	protected Item getItem(boolean isIngredient, String name){
		if(isIngredient){
			return ingredients.get(name);
		}
		else{
			return items.get(name);
		}
	}


	protected ArrayList<Item> getAllIngredients(){
		try{
			ArrayList<Item> result = new ArrayList<Item>(ingredients.values());
			return result;
		}
		catch(NullPointerException e){
			return new ArrayList<Item>();
		}
	}
	
	protected ArrayList<Item> getAllBaked(){
		try{
			ArrayList<Item> result = new ArrayList<Item>(items.values());
			return result;
		}
		catch(NullPointerException e){
			return new ArrayList<Item>();
		}
	}

	protected boolean newItem(boolean isIngredient, String name, String unit, int low, int quantity, double price){
		Item thing = new Item(isIngredient, name, unit, low, quantity, price);
		if(isIngredient){
			if(ingredients.get(name) == null) {
				ingredients.put(name, thing);
				return true;
			}
			return false;
		}
		else{
			if(items.get(name) == null) {
				items.put(name, thing);
				return true;
			}
			return false;
		}

		//incomplete implementation - [demo version]
		//	run database sync job updating the underlying file structure
		//	this will be done in background

	}

}