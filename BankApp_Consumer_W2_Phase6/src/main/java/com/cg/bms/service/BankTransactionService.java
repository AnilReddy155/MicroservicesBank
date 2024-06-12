package com.cg.bms.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.bms.dto.TransactionDto;

@FeignClient(name = "transaction-service")
public interface BankTransactionService {
	
	@GetMapping("/api/v1/transactions/{accountNumber}/last10")
	ResponseEntity<List<TransactionDto>> getTransactions(@PathVariable Long accountNumber);
	
	@PostMapping("/api/v1/transactions/add")
	ResponseEntity<TransactionDto> addTransaction(@RequestBody TransactionDto transaction);

}
