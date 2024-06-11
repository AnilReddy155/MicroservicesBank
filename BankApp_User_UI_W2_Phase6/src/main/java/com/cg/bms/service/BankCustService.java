package com.cg.bms.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.bms.model.Account;
import com.cg.bms.model.Customer;
import com.cg.bms.model.DepositRequest;
import com.cg.bms.model.Transaction;
import com.cg.bms.model.TransferRequest;
import com.cg.bms.model.WithdrawRequest;

@FeignClient(name = "API-GATWAY-SERVICE", url = "http://localhost:8081")
public interface BankCustService {
	
	@PostMapping("/consumer-service/api/v1/banking/login")
	Customer findByUserNameAndPassword(@RequestBody Customer customer);
	
	@GetMapping("/consumer-service/api/v1/banking/customer/{custId}")
	Account findByCustId(@PathVariable Long custId);
	
	@PostMapping("/consumer-service/api/v1/banking/{accountNumber}/withdraw")
	ResponseEntity<Account> withdraw(@PathVariable Long accountNumber, @RequestBody WithdrawRequest request);

	@PostMapping("/consumer-service/api/v1/banking/{accountNumber}/deposit")
	Account deposit(@PathVariable Long accountNumber,@RequestBody DepositRequest request);
	
	@PostMapping("/consumer-service/api/v1/banking/{fromAccountNumber}/deposit")
	Account transfer(@PathVariable Long fromAccountNumber,@RequestBody TransferRequest request) ;
	
	@GetMapping("/consumer-service/api/v1/banking/{accountNumber}/transactions")
	ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long accountNumber);
	
	@GetMapping("/consumer-service/api/v1/banking/{accountNumber}/balance")
	Double getBalance(@PathVariable Long accountNumber) ;
}
