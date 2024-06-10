package com.cg.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bms.dto.TransactionDto;
import com.cg.bms.service.TransactionMgmService;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionMgmService transactionService;

	// create endpoint to get transaction by account number
	@GetMapping("/{accountNumber}")
	public List<TransactionDto> getTransactions(@PathVariable Long accountNumber) {
		return transactionService.getTransactions(accountNumber);
	}
	// create endpoint to add transaction
	@PostMapping("/add")
	public TransactionDto addTransaction(@RequestBody TransactionDto transaction) {
		return transactionService.addTransactions(transaction);
	}
	// create endpoint to update transaction
	/*
	 * @PutMapping("/update") public TransactionDto updateTransaction(TransactionDto
	 * transaction) { return transactionService.addTransactions(transaction); }
	 */
	// create endpoint to get last 10 transactions
	@GetMapping("/{accountNumber}/last10")
	public List<TransactionDto> getLast10Transactions(@PathVariable Long accountNumber) {
		return transactionService.getLst10Transactions(accountNumber);
	}
	
}
