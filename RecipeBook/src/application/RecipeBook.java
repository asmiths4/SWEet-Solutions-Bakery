package application;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;

public class RecipeBook {
	
	public RecipeBook() {
		
	}
	
	public class Pair<K,V>{ //pair class for holding recipe descriptions
		private K key; //holds title of ingredient
		private V value; //holds amount of ingredient to be used
		
		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public void setKey(K key) {
			this.key = key;
		}
		
		public void setValue(V value) {
			this.value = value;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		@Override
		public String toString() {
			String toReturn = "<" + (String) this.getKey() + ", " + (String) this.getValue() + "> ";
			return toReturn;
		}
	}
	
	@FXML
	private TextField name;
	@FXML
	private TextField ingredient;
	@FXML
	private TextField ingredient1;
	@FXML
	private TextField ingredient2;
	@FXML
	private TextField amount;
	@FXML
	private TextField amount1;
	@FXML
	private TextField amount2;
	
	@FXML
	public void addRecipe() throws IOException { //adds a new recipe to the text file
		
		File recipeList = new File("recipeList.txt"); //text file to write to
		PrintWriter toWrite = new PrintWriter(new FileWriter(recipeList, true));
		ArrayList<Pair<String, String>> recipe = new ArrayList<Pair<String,String>>(); //list of pairs that make up new recipe
		
		String title = name.getText();
		String ing = ingredient.getText(); //grab the value from each text field
		String ing1 = ingredient1.getText();
		String ing2 = ingredient2.getText();
		String amt = amount.getText();
		String amt1 = amount1.getText();
		String amt2 = amount2.getText();
		
		recipe.add(new Pair<String,String>(title, "")); //add title of recipe as first pair
		if(checkNotEmpty(ing, amt)) {recipe.add(new Pair<String,String>(ing, amt));} //make sure not empty before adding to array list
		if(checkNotEmpty(ing1, amt1)) {recipe.add(new Pair<String,String>(ing1, amt1));}
		if(checkNotEmpty(ing2, amt2)) {recipe.add(new Pair<String,String>(ing2, amt2));}

		toWrite.append(recipe.get(0).getKey() + ": ");
		for(int i = 1; i < recipe.size(); i++) { //write Pair<K,V> to file for length of array list
				toWrite.append(recipe.get(i).toString());
		}
		
		toWrite.println();
		toWrite.close(); //close print writer
	}
	
	public boolean checkNotEmpty(String i, String a) {//helper to check if text fields are empty. don't want to add to array list if empty
		if(i != null && !(i.trim().isEmpty())) {
			if(a != null && !(a.trim().isEmpty())) {
				return true;
			}
		}
		return false;
	}
	
	@FXML 
	private TextArea displayText;
	
	@FXML
	public void printRecipes() throws IOException { //grabs list of recipes from text file and prints them to GUI
		
		BufferedReader reader;
		File file = new File("C:/Users/Amanda/eclipse-workspace/RecipeBook/recipeList.txt");
		reader = new BufferedReader(new FileReader(file));

		String recipeLine;
		while ((recipeLine = reader.readLine()) != null) {
			displayText.appendText(recipeLine);
			displayText.appendText("\n");
		}
		reader.close();
	}
	
	public String line; //store file line

	public boolean checkRecipe(String rec) throws IOException { //will call public int getAmountIngredient(String name)
		boolean status = false;
		
		BufferedReader reader;
		File file = new File("C:/Users/Amanda/eclipse-workspace/RecipeBook/recipeList.txt");
		reader = new BufferedReader(new FileReader(file));
		
		while ((line = reader.readLine()) != null) { //search file for recipe.
			//System.out.println(line);
			if(line.contains(rec)) {//break so we keep the line the recipe is on.
				break;
			}
		}

		ArrayList<String> ingredients = new ArrayList<String>();
		ArrayList<String> amounts = new ArrayList<String>();

		Pattern pattern = Pattern.compile("<(.*?),"); //grabs all ingredients from the recipe and stores them in array list
		Matcher matcher = pattern.matcher(line);
		while(matcher.find()) {
			ingredients.add(matcher.group(1));
		}
		
		Pattern pattern1 = Pattern.compile(",(.*?)>"); //grabs all amounts from the recipe and stores them in array list
		Matcher matcher1 = pattern1.matcher(line);
		while(matcher1.find()) {
			amounts.add(matcher1.group(1).trim());
		}
		
		//Inventory inv = new Inventory();
		/*for(int i = 0; i < ingredients.size(); i++) {
			if(Integer.parseInt(amounts.get(i)) >= inv.business.getAmountIngredient(ingredients.get(i))) {
				status = true;
			}
			else {
				status = false;
			}
		}*/
		
		reader.close();
		return status;
	}
	
	
	
	
	
}
