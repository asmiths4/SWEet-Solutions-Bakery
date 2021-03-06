package customOrders;

import java.io.IOException;

import application.*;

public class customOrdersModel {
    //This Class is intended to be the model part of the MVC. This class talks to the recipe book to find out the availability of the ingredients used in the custom order.
	public static String[] checkAvailability(boolean chocolate,boolean vanilla,boolean carrot,boolean redVelvet,boolean small,boolean medium,boolean large,boolean creamCheese,boolean ganache,boolean butterCream,boolean grahamCrackers,boolean cocoa,boolean whippedCream,boolean strawberries,String specialInstructions) throws IOException {
    	String cake;
    	String size;
    	String icing;
    	String toppings;
    	String[] information = new String[6];
    	boolean availableCake = true;
    	boolean availableIcing = true;
    	boolean availableGrahamCrackers = true;
    	boolean availableStrawberries = true;
    	boolean availableWhippedCream = true;
    	boolean availableCocoa = true;
    	boolean available;
    	recipeBook.RecipeBook r = new recipeBook.RecipeBook();
    	
    	//Find out which type of cake they want
    	if(chocolate) {
    		cake = "chocolate";
    		information[1] = "Chocolate";
    	}
    	else {
    		if(redVelvet) {
    			cake = "redvelvet";
    			information[1] = "Red Velvet";
    		}
    		else {
    			if(carrot) {
    				cake = "carrot";
    				information[1] = "Carrot";
    			}
    			else {
    				cake = "vanilla";
    				information[1] = "Vanilla";
    			}
    		}
    	}
    	//Find out which size they want
    	if(large) {
    		size = "large";
    		information[2] = "Large";
    	}
    	else {
    		if(medium) {
    			size = "medium";
    			information[2] = "Medium";
    		}
    		else {
    			size = "small";
    			information[2] = "Small";
    		}
    	}
    	//Find out which kind of icing they want
    	if(creamCheese) {
    		icing = "creamcheese";
    		information[3] = "Cream Cheese";
    	}
    	else {
    		if(butterCream) {
    			icing = "buttercream";
    			information[3] = "Buttercream";
    		}
    		else {
    			if(ganache) {
    			  icing = "ganache";
    			  information[3] = "Ganache";
    			}
    			else {
    			  icing = "none";
    			  information[3] = "";
    			}
    		}
    	}
    	
    	//Find out which topping they want
		toppings = "none";
		information[4] = "";
    	if(grahamCrackers) {
    		toppings = "grahamcracker";
    		information[4] = information[4]+" Graham Crackers";
    		availableGrahamCrackers = r.checkRecipe(toppings);
    	}
        if(strawberries) {
    		toppings = "strawberries";
    		information[4] = information[4]+" Strawberries";
    	    availableStrawberries = r.checkRecipe(toppings);
    	}
    	if(whippedCream) {
    		toppings = "whippedcream";
    		information[4] = information[4]+" Whipped Cream";
            availableWhippedCream = r.checkRecipe(toppings);
    	}
    	if(cocoa) {
    	  toppings = "cocoa";
    	  information[4] = information[4]+" Cocoa";
    	  availableCocoa = r.checkRecipe(toppings);
    	}

    	
		//Send information to the recipe system
    	availableCake = r.checkRecipe(cake);
    	availableIcing = r.checkRecipe(icing);
    	
    	available = availableCake&&availableIcing&&availableGrahamCrackers&&availableCocoa&&availableWhippedCream&&availableStrawberries;
    	
    	//Finish the array of information to return to the controller
    	if(available) {
    		information[0] = "true";
    	}
    	else {
    	    information[0] = "false";
    	}
    	
    	information[5] = specialInstructions;
    	
		return information;
	}
	
}
