package com.cg.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.bms.config.PropertiesConfiguration;
import com.cg.bms.model.Account;
import com.cg.bms.model.Customer;
import com.cg.bms.model.DepositRequest;
import com.cg.bms.model.Transaction;
import com.cg.bms.model.TransferRequest;
import com.cg.bms.model.WithdrawRequest;

@Service
public class BankCustomerService {
	
	//add method to call account service using resttemplete
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private PropertiesConfiguration properties;
	
	
	public Customer findByUserNameAndPassword(Customer customer) {
		String url = properties.getConsumerUri()+"/login";
		Customer result = restTemplate.postForObject(url, customer, Customer.class);
		return result;
	}
	
	public Account findByCustId(Long custId) {
		String url = properties.getConsumerUri()+ "/customer/"+custId;
		Account result = restTemplate.getForObject(url, Account.class);
		return result;
	}
	
	public ResponseEntity<Account> withdraw(Long accountNumber, Double amount) {
		String url = properties.getConsumerUri() +"/"+accountNumber+ "/withdraw";
		WithdrawRequest request = new WithdrawRequest();
		request.setAmount(amount);
		return restTemplate.postForEntity(url, request, Account.class);
		
	}
	
	public Account deposit(Long accountNumber, Double amount) {
		String url = properties.getConsumerUri() +"/"+accountNumber+ "/deposit";
		DepositRequest request = new DepositRequest();
		request.setAmount(amount);
		Account result = restTemplate.postForObject(url, request, Account.class);
		return result;
	}
	
	public Account transfer(Long fromAccountNumber, Long toAccountNumber, Double amount) {
		String url = properties.getConsumerUri() + fromAccountNumber+ "/transfer";
		System.out.println("url: "+url);
		TransferRequest request = new TransferRequest();
		request.setAmount(amount);
		request.setTargetAccount(toAccountNumber);
		Account result = restTemplate.postForObject(url, request, Account.class);
		return result;
	}
	
	public ResponseEntity<List<Transaction>> getTransactions(Long accountNumber) {
		return restTemplate.exchange(properties.getConsumerUri() + accountNumber + "/transactions", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Transaction>>() {
				});
	}
	
	//get balance
	public Double getBalance(Long accountNumber) {
		String url = properties.getConsumerUri() + accountNumber + "/balance";
		Double result = restTemplate.getForObject(url, Double.class);
		return result;
	}

}
