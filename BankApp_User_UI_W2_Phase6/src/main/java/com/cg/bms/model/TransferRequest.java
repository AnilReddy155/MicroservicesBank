package com.cg.bms.model;

public class TransferRequest {


	private double amount;

	private Long targetAccount;

	public TransferRequest() {
		super();
	}

	public TransferRequest(double amount, Long targetAccount) {
		super();
		this.amount = amount;
		this.targetAccount = targetAccount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Long getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(Long targetAccount) {
		this.targetAccount = targetAccount;
	}
}
