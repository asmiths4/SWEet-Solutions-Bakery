package pos;

//class to hold item data
class TItem {
	protected int amount;
	protected String name;
	protected double price;
	
	//constructor for new item
	protected TItem() {
		this.amount = 0;
		this.name = "";
		this.price = 0.0;
	}
}
