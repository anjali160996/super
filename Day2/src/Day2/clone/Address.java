package Day2.clone;

public class Address implements Cloneable {
	private String city;
	private String zip;
	private String country;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Address(String city, String zip,String country) {
		this.city = city;
		this.zip = zip;
		this.country=country;
	}

	public Address(Address other) {
		this.city = other.city;
		this.zip = other.zip;
		this.country =other.zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}
	@Override
	protected Address clone() throws CloneNotSupportedException {
		return (Address) super.clone(); 
	}

}
