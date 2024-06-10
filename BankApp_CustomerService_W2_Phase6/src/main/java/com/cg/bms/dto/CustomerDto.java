package com.cg.bms.dto;

import com.cg.bms.entity.Customer;

public class CustomerDto {

	private Long customerId;
	private String userName;
	private String fullName;
	private String password;
	private String email;
	private String phoneNumber;

	public CustomerDto() {
	}

	public CustomerDto(Customer customer) {
		this.customerId = customer.getCustomerId();
		this.userName = customer.getUserName();
		this.fullName = customer.getFullName();
		this.password = customer.getPassword();
		this.email = customer.getEmail();
		this.phoneNumber = customer.getPhoneNumber();

	}

	public CustomerDto(Long customerId, String userName, String fullName, String password, String email,
			String phoneNumber) {
		super();
		this.customerId = customerId;
		this.userName = userName;
		this.fullName = fullName;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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
		return "CustomerDto [customerId=" + customerId + ", userName=" + userName + ", fullName=" + fullName
				+ ", password=" + password + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}

}
