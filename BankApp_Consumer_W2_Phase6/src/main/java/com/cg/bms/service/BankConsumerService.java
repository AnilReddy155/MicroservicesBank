package com.cg.bms.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.bms.dto.AccountDto;
import com.cg.bms.model.DepositRequest;
import com.cg.bms.model.TransferRequest;
import com.cg.bms.model.WithdrawRequest;

@FeignClient(name = "account-service")
public interface BankConsumerService {
	
	@GetMapping("/api/v1/accounts/{accountNumber}/balance")
	Double getBalance(@PathVariable Long accountNumber);
	
	@PostMapping("/api/v1/accounts/{accountNumber}/deposit")
	ResponseEntity<AccountDto> deposit(@PathVariable Long accountNumber,@RequestBody DepositRequest depositRequest);
	
	@PostMapping("/api/v1/accounts/{accountNumber}/withdraw")
	ResponseEntity<AccountDto> withdraw(@PathVariable Long accountNumber,@RequestBody WithdrawRequest withdrawRequest);
	
	@PostMapping("/api/v1/accounts/{fromAccountNumber}/transfer")
	ResponseEntity<AccountDto> transfer(@PathVariable Long fromAccountNumber,@RequestBody TransferRequest transferRequest);
	
	@GetMapping("/api/v1/accounts/customer/{custId}")
	ResponseEntity<AccountDto> getAccountByCustId(@PathVariable Long custId);
}
