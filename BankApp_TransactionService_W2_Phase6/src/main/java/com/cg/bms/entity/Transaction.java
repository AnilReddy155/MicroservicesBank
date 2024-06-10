package com.cg.bms.entity;

import java.util.Date;

import com.cg.bms.dto.TransactionDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction", schema = "bms_trnx_db")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trnx_seq")
	@SequenceGenerator(name = "trnx_seq", sequenceName = "trnx_sequence", allocationSize = 1, schema = "bms_trnx_db")
	@Column(name = "trnx_id")
	private Long trnxId;
	@Column(name = "trnx_type")
	private String type;
	@Column(name = "amount")
	private double amount;
	@Column(name = "remaining_balance")
	private double remainingBalance;
	@Column(name = "timestamp")
	private Date timestamp;
	@Column(name = "status")
	private String status;

	@Column(name = "account_number")
	private Long accountNumber;
	
	public Transaction() {
	}

	public Transaction(TransactionDto trnx) {
		this.trnxId = trnx.getTrnxId();
		this.type = trnx.getType();
		this.amount = trnx.getAmount();
		this.remainingBalance = trnx.getRemainingBalance();
		this.timestamp = trnx.getTimestamp();
		this.status = trnx.getStatus();
		this.accountNumber = trnx.getAccountNumber();
		this.timestamp = new Date();
	}

	public Transaction(String type, double amount, double remainingBalance, String status, Long accountNumber) {
		super();
		this.type = type;
		this.amount = amount;
		this.remainingBalance = remainingBalance;
		this.status = status;
		this.timestamp = new Date();
		this.accountNumber = accountNumber;
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

	public Long getTrnxId() {
		return trnxId;
	}

	public void setTrnxId(Long trnxId) {
		this.trnxId = trnxId;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "Transaction [trnxId=" + trnxId + ", type=" + type + ", amount=" + amount + ", remainingBalance="
				+ remainingBalance + ", timestamp=" + timestamp + ", status=" + status + ", accountNumber="
				+ accountNumber + "]";
	}

}
