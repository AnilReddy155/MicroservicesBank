package com.cg.bms.model;

public class Customer {

	private Long customerId;
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String phoneNumber;

	public Customer() {
	}

	public Customer(Long customerId, String userName, String firstName, String lastName, String password, String email,
			String phoneNumber) {
		super();
		this.customerId = customerId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", userName=" + userName + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", password=" + password + ", email=" + email + ", phoneNumber="
				+ phoneNumber + "]";
	}

}
