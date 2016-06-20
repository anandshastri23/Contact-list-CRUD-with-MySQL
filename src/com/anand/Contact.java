package com.anand;

public class Contact {
	
	private int id;
	private String firstName;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String lastName;
	private String contactNumber;
	private String emailId;
	
	public Contact(int id, String firstName, String lastName, String contactNumber, String emailId){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNumber = contactNumber;
		this. emailId = emailId;	
	}
	
	public Contact(String firstName, String lastName, String contactNumber, String emailId){
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNumber = contactNumber;
		this. emailId = emailId;	
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", contactNumber=" + contactNumber + ", emailId="
				+ emailId + "]";
	}
	
	
	

}
