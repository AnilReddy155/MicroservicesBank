package com.cg.bms.entity;

import com.cg.bms.dto.AccountDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "account", schema = "bms_acc_db")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
	@SequenceGenerator(name = "account_seq", sequenceName = "account_sequence", allocationSize = 1, schema = "bms_acc_db")
	@Column(name = "account_number")
	private Long accountNumber;
	@Column(name = "account_type")
	private String accountType;
	private double balance;
	@Column(name = "customer_id", nullable = false, unique = true)
	private Long customerId;

	public Account() {
	}

	public Account(AccountDto accountDto) {

		this.accountNumber = accountDto.getAccountNumber();
		this.accountType = accountDto.getAccountType();
		this.balance = accountDto.getBalance();
		this.customerId = accountDto.getCustomerId();
	}

	public Account(Long accountNumber, String accountType, double balance, Long customerId) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
		this.customerId = customerId;
	}

	public Account(String accountType, double balance, Long customerId) {
		super();
		this.accountType = accountType;
		this.balance = balance;
		this.customerId = customerId;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountType=" + accountType + ", balance=" + balance
				+ ", customerId=" + customerId + "]";
	}

}
