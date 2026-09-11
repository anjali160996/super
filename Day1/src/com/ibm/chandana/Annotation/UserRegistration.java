package com.ibm.chandana.Annotation;

import java.util.List;

public class UserRegistration {
	@NotNull
	private String name;
	@NotNull
	private String email;
	@NotNull
	private List<String> phoneNumbers ;
	public UserRegistration(String name, String email,List<String> numbers) {
		this.email=email;
		this.name=name;
		this.phoneNumbers=numbers;
	}
	@Override
	public String toString() {
		return "UserRegistration [name=" + name + ", email=" + email + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
