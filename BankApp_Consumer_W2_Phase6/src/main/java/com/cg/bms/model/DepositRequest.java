package com.cg.bms.model;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated

public class DepositRequest {

	@Positive(message = "Amount should be positive")
	@NotNull(message = "Amount is required")
	private double amount;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
