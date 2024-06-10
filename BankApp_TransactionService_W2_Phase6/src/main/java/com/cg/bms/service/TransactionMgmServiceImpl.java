package com.cg.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cg.bms.dto.TransactionDto;
import com.cg.bms.entity.Transaction;
import com.cg.bms.repository.TransactionsRepository;

@Service
public class TransactionMgmServiceImpl implements TransactionMgmService {
	@Autowired
	private TransactionsRepository transactionsRepository;

	@Override
	public List<TransactionDto> getTransactions(Long accountNumber) {
		return transactionsRepository.findTransactionsByAccountNumber(accountNumber)
				.stream().map(TransactionDto::new).toList();
	}

	@Override
	public TransactionDto addTransactions(TransactionDto trnx) {
		return new TransactionDto(transactionsRepository.save(new Transaction(trnx)));
	}

	@Override
	public List<TransactionDto> getLst10Transactions(Long accountNumber) {
		return transactionsRepository.findLast10TransactionsByAccountNumber(accountNumber, PageRequest.of(0, 10))
				.stream().map(TransactionDto::new).toList();

	}

	@Override
	public List<TransactionDto> getTransactionsByType(Long accountNumber, String type) {
		return transactionsRepository.findTransactionsByAccountNumberAndType(accountNumber, type).stream()
				.map(TransactionDto::new).toList();
	}

	@Override
	public List<TransactionDto> getTransactionsByStatus(Long accountNumber, String status) {
		return transactionsRepository.findTransactionsByAccountNumberAndStatus(accountNumber, status).stream()
				.map(TransactionDto::new).toList();
	}

}
