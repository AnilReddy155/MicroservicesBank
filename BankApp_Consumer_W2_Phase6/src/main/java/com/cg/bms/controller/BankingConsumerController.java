package com.cg.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bms.dto.AccountDto;
import com.cg.bms.dto.CustomerDto;
import com.cg.bms.dto.TransactionDto;
import com.cg.bms.model.DepositRequest;
import com.cg.bms.model.TransferRequest;
import com.cg.bms.model.WithdrawRequest;
import com.cg.bms.service.BankConsumerService;
import com.cg.bms.service.BankingConsumerService;
import com.cg.bms.validation.AccountNumberValidation;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/banking")
@Validated
public class BankingConsumerController {

	// private static final String ACCOUNT_BASE_URL =
	// "http://localhost:5050/api/v1/accounts/";

	@Autowired
	private BankingConsumerService bankingConsumerService;
	
	@Autowired
	private BankConsumerService bankConsumerService;

	// create endpoint to get account balance
	@GetMapping("/{accountNumber}/balance")
	public Double getBalance(@Valid @AccountNumberValidation(min = 10) @PathVariable Long accountNumber) {
		return bankConsumerService.getBalance(accountNumber);
	}

	// create endpoint to deposit amount
	@PostMapping("/{accountNumber}/deposit")
	public ResponseEntity<AccountDto> deposit( 
			@Valid @AccountNumberValidation(min = 10) @PathVariable Long accountNumber,
			@Valid @RequestBody DepositRequest depositRequest) {
		return bankingConsumerService.deposit(accountNumber, depositRequest);
	}

	// create endpoint to withdraw amount
	@PostMapping("/{accountNumber}/withdraw")
	public ResponseEntity<AccountDto> withdraw(
			@Valid @AccountNumberValidation(min = 10) @PathVariable Long accountNumber,
			@Valid @RequestBody WithdrawRequest withdrawRequest) {
		return bankingConsumerService.withdraw(accountNumber, withdrawRequest);
	}

	// create endpoint to transfer amount
	@PostMapping("/{fromAccountNumber}/transfer")
	public ResponseEntity<AccountDto> transfer(
			@PathVariable @AccountNumberValidation(min = 10) Long fromAccountNumber,
			@Valid @RequestBody TransferRequest transferRequest) {
		return bankingConsumerService.transfer(fromAccountNumber, transferRequest);
	}

	// create endpoint to get last 10 transactions for an account
	@GetMapping("/{accountNumber}/transactions")
	public ResponseEntity<List<TransactionDto>> getTransactions(@Valid
			@AccountNumberValidation(min = 10) @PathVariable Long accountNumber) {
		return bankingConsumerService.getTransactions(accountNumber);
	}

	// create endpoint to add transaction
	@PostMapping("/transactions/add")
	public ResponseEntity<TransactionDto> addTransaction(@RequestBody TransactionDto transaction) {
		return bankingConsumerService.addTransaction(transaction);
	}
	
	@PostMapping("/login")
	public CustomerDto findByUserNameAndPassword(@RequestBody CustomerDto customerDto) {
		return bankingConsumerService.findByUserNameAndPassword(customerDto);
	}
	
	@GetMapping("/customer/{custId}")
	public ResponseEntity<AccountDto> getAccountByCustId(@PathVariable Long custId) {
		return bankingConsumerService.getAccountByCustId(custId);
	}
}
