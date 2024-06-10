package com.cg.bms.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class WithdrawRequest {

	@NotNull
	@Positive(message = "Amount should be positive")
	private double amount;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
