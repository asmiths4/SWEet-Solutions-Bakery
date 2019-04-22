package pos;

import java.util.ArrayList;

//class to hold transaction data
class Transaction {
	protected double total;
	protected double taxTotal;
	protected double tax;
	protected ArrayList<TItem> items;
	
	//constructor for a blank transaction
	protected Transaction() {
		this.total = 0.0;
		this.taxTotal = 0.0;
		this.tax = 0.0;
		this.items = new ArrayList<>();
	}
}
