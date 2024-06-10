package com.cg.bms.model;

import com.cg.bms.validation.AccountNumberValidation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class TransferRequest {

	@NotNull
	@Positive(message = "Amount should be positive")
	private double amount;
	@NotNull
	@AccountNumberValidation(min = 10)
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
