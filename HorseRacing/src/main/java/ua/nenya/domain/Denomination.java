package ua.nenya.domain;

public class Denomination {

	private String name;
	private int amount;
	private int quantity;
	private int initialQuantity;

	public Denomination(String name, int amount, int initialQuantity) {
		this.name = name;
		this.amount = amount;
		this.initialQuantity = initialQuantity;
	}

	public Denomination(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public int getAmount() {
		return amount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getInitialQuantity() {
		return initialQuantity;
	}

}
