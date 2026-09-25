package com.ibm.chandana.Generics;

public class User implements Identifiable {
	private Long id;
	private String name;
	private String emailId;

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmailId() {
		return emailId;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

	public User(Long id, String name, String emailId) {
		this.id = id;
		this.name = name;
		this.emailId = emailId;
	}

	public User(long l, String name) {
		this.id = l;
		this.name = name;
	}

}
