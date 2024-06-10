package com.cg.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.cg.bms.config.PropertiesConfiguration;
import com.cg.bms.dto.AccountDto;
import com.cg.bms.dto.CustomerDto;
import com.cg.bms.dto.TransactionDto;
import com.cg.bms.model.DepositRequest;
import com.cg.bms.model.TransferRequest;
import com.cg.bms.model.WithdrawRequest;

@Service
public class BankingConsumerService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private PropertiesConfiguration properties;

	public Double getBalance(Long accountNumber) {
		return restTemplate.getForObject(properties.getAccountUri() + accountNumber + "/balance", Double.class);
	}

	public ResponseEntity<AccountDto> deposit(Long accountNumber, DepositRequest depositRequest) {
		ResponseEntity<AccountDto> responseEntity = restTemplate.postForEntity(
				properties.getAccountUri() + accountNumber + "/deposit", depositRequest, AccountDto.class);
		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			TransactionDto transaction = new TransactionDto();
			transaction.setAccountNumber(accountNumber);
			transaction.setAmount(depositRequest.getAmount());
			transaction.setType("Credit");
			transaction.setStatus("Success");
			addTransaction(transaction);

		} else {
			TransactionDto transaction = new TransactionDto();
			transaction.setAccountNumber(accountNumber);
			transaction.setAmount(depositRequest.getAmount());
			transaction.setType("Credit");
			transaction.setStatus("Failed");
			addTransaction(transaction);
		}
		return responseEntity;

	}

	@PostMapping("/{accountNumber}/withdraw")
	public ResponseEntity<AccountDto> withdraw(Long accountNumber, WithdrawRequest withdrawRequest) {
		ResponseEntity<AccountDto> responseEntity = restTemplate.postForEntity(
				properties.getAccountUri() + accountNumber + "/withdraw", withdrawRequest, AccountDto.class);
		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			TransactionDto transaction = new TransactionDto();
			transaction.setAccountNumber(accountNumber);
			transaction.setAmount(withdrawRequest.getAmount());
			transaction.setRemainingBalance(responseEntity.getBody().getBalance());
			transaction.setType("Debit");
			transaction.setStatus("Success");
			addTransaction(transaction);

		} else {
			TransactionDto transaction = new TransactionDto();
			transaction.setAccountNumber(accountNumber);
			transaction.setAmount(withdrawRequest.getAmount());
			transaction.setRemainingBalance(withdrawRequest.getAmount());
			transaction.setType("Debit");
			transaction.setStatus("Failed");
			addTransaction(transaction);
		}

		return responseEntity;
	}

	public ResponseEntity<AccountDto> transfer(Long fromAccountNumber, TransferRequest transferRequest) {
		System.out.println("fromAccountNumber : " + fromAccountNumber + " transferRequest : " + transferRequest);
		ResponseEntity<AccountDto> responseEntity = restTemplate.postForEntity(
				properties.getAccountUri() + fromAccountNumber + "/transfer", transferRequest, AccountDto.class);
		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			saveTransaction(fromAccountNumber, transferRequest, "Success");

		} else {
			saveTransaction(fromAccountNumber, transferRequest, "Failed");
		}

		return responseEntity;
	}

	private void saveTransaction(Long fromAccountNumber, TransferRequest transferRequest, String status) {
		TransactionDto transaction = new TransactionDto();
		transaction.setAccountNumber(fromAccountNumber);
		transaction.setAmount(transferRequest.getAmount());
		transaction.setType("Debit");
		transaction.setStatus(status);
		addTransaction(transaction);
		TransactionDto tran = new TransactionDto();
		tran.setAccountNumber(transferRequest.getTargetAccount());
		tran.setAmount(transferRequest.getAmount());
		tran.setType("Credit");
		tran.setStatus(status);
		addTransaction(tran);
	}

	public ResponseEntity<List<TransactionDto>> getTransactions(Long accountNumber) {
		return restTemplate.exchange(properties.getTransactionUri() + accountNumber + "/last10", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<TransactionDto>>() {
				});
	}

	public ResponseEntity<TransactionDto> addTransaction(TransactionDto transaction) {
		return restTemplate.postForEntity(properties.getTransactionUri() + "/add", transaction, TransactionDto.class);
	}
	
	public CustomerDto findByUserNameAndPassword(@RequestBody CustomerDto customerDto) {
		return restTemplate.postForObject(properties.getCustomerUri()+"/login", customerDto, CustomerDto.class);
	}

	public ResponseEntity<AccountDto> getAccountByCustId(Long custId) {
		return restTemplate.getForEntity(properties.getAccountUri()+"/customer/"+custId, AccountDto.class);
	}
}
