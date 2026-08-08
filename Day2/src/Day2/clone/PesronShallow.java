package Day2.clone;

public class PesronShallow implements Cloneable {
	private String name;
	private Address address;

	public PesronShallow(String name, Address address) {
		this.name = name;
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	protected PesronShallow clone() throws CloneNotSupportedException {
		return (PesronShallow) super.clone(); 
	}
}
