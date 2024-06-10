package com.cg.bms.service;

import java.util.List;

import com.cg.bms.dto.TransactionDto;

public interface TransactionMgmService {
	TransactionDto addTransactions(TransactionDto trnx);

	List<TransactionDto> getTransactions(Long accountNumber);

	List<TransactionDto> getLst10Transactions(Long accountNumber);

	List<TransactionDto> getTransactionsByType(Long accountNumber, String type);

	List<TransactionDto> getTransactionsByStatus(Long accountNumber, String status);
}
