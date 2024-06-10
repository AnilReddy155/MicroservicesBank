package com.cg.bms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer", schema = "bms_cust_db")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
	@SequenceGenerator(name = "customer_seq", sequenceName = "customer_sequence", allocationSize = 1, schema = "bms_cust_db")
	@Column(name = "customer_id")
	private Long customerId;
	@Column(name = "user_name" , nullable = false, unique = true)
	private String userName;
	@Column(name = "full_name")
	private String fullName;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	@Column(name = "phone_number")
	private String phoneNumber;

	public Customer(Long customerId, String userName, String fullName, String password, String email,
			String phoneNumber) {
		super();
		this.customerId = customerId;
		this.userName = userName;
		this.fullName = fullName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public Customer() {
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
		return "Customer [customerId=" + customerId + ", userName=" + userName + ", fullName=" + fullName
				+ ", password=" + password + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}

}
