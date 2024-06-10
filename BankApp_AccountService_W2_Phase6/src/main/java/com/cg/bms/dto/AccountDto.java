package com.cg.bms.dto;

import com.cg.bms.entity.Account;

public class AccountDto {
	private Long accountNumber;
	private String accountType;
	private double balance;
	private Long customerId;

	public AccountDto() {
	}
	
	public AccountDto(Account account) {
		this.accountNumber = account.getAccountNumber();
		this.accountType = account.getAccountType();
		this.balance = account.getBalance();
		this.customerId = account.getCustomerId();
	}

	public AccountDto(Long accountNumber, String accountType, double balance, Long customerId) {
		super();
		this.accountNumber = accountNumber;
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
