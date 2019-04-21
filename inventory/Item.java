package inventory;

public class Item{
	protected final boolean INGREDIENT;
	protected final String NAME;
	protected final String UNITS;
	protected final Integer LOW_LVL;
	private int quantity;
	private double price;

	protected Item(boolean isIngredient, String name, String unit, int low, int q, double p){
		INGREDIENT = isIngredient;
		NAME = name;
		UNITS = unit;
		LOW_LVL = low;
		quantity = q;
		price = p;
	}

	protected boolean isLow(){
		if(LOW_LVL != null){
			if(quantity <= LOW_LVL){
				return true;
			}
			else{
				return false;
			}
		}
		return true;
	}


	public String getName() {
		return NAME;
	}
	
	//requires that a verification is done, throws an exception if operation cannot be performed
	protected int update(boolean isRemoving, int amount){
		if(amount < 0){
			throw new IllegalArgumentException("Cannot perform updates to Item with negative values!");
		}

		if(isRemoving){
			if(this.quantity - amount >= 0){
				quantity -= amount;
				return quantity;
			}
			else{
				throw new IllegalArgumentException("Cannot withdraw more than we have. Amount: " + Integer.toString(quantity) + ", trying to withdraw: " + Integer.toString(amount));
			}
		}
		else{
			//adding
			quantity += amount;
			return quantity;
		}
	}

	protected double getPrice(){
		return price;
	}
	
	public int getQuantity(){
		return quantity;
	}

}//end class
