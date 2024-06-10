package com.cg.bms.model;

import java.util.Date;

public class Transaction {

	private Long trnxId;
	private String type;
	private double amount;
	private double remainingBalance;
	private Date timestamp;
	private String status;
	private Long accountNumber;

	public Transaction() {
	}

	public Transaction(String type, double amount, double remainingBalance, String status, Long accountNumber) {
		super();
		this.type = type;
		this.amount = amount;
		this.remainingBalance = remainingBalance;
		this.status = status;
		this.accountNumber = accountNumber;
		this.timestamp = new Date();
	}

	public Long getTrnxId() {
		return trnxId;
	}

	public void setTrnxId(Long trnxId) {
		this.trnxId = trnxId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getRemainingBalance() {
		return remainingBalance;
	}

	public void setRemainingBalance(double remainingBalance) {
		this.remainingBalance = remainingBalance;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "Transaction [type=" + type + ", amount=" + amount + ", remainingBalance=" + remainingBalance
				+ ", timestamp=" + timestamp + ", status=" + status + ", accountNumber=" + accountNumber + "]";
	}

}
