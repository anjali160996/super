package Day2.clone;

public class Person implements Cloneable {
	private String name;
	private Address address;

	public Person(String name, Address address) {
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
	protected Person clone() throws CloneNotSupportedException {
		Person copy = (Person) super.clone();
		copy.address = address.clone(); // Deep clone
		return copy;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", address=" + address + "]";
	}
}

//
//// shallow clone
//public Person shallowClone() {
//	return new Person(this.name, this.address);
//}
//
//// deepclone
//public Person deepClone() {
//	return new Person(this.name, new Address(this.address));
//}
