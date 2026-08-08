package Java5;

public class Order {
	private int id;
	private String name;
	private String region;
	private double amount;

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Order(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Order(int id, String name, String region, double amount) {
		this.id = id;
		this.name = name;
		this.region = region;
		this.amount = amount;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Order[id=" + id + ", name=" + name + ", region=" + region + ", amount=" + amount + "]";
	}

}
