package com.cg.bms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

import com.cg.bms.exception.GlobalException;
import com.cg.bms.model.Account;
import com.cg.bms.model.Customer;
import com.cg.bms.model.ErrorResponse;
import com.cg.bms.model.Transaction;
import com.cg.bms.service.BankCustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/customer")
public class BankingCustomerController {

	private static final Logger logger = LoggerFactory.getLogger(BankingCustomerController.class);

	@Autowired
	private BankCustomerService bankCustomerService;

	@PostMapping("/home")
	public String validateLoginForm(@ModelAttribute Customer customer, Model model, HttpServletRequest request) {
		try {
			Customer cust = bankCustomerService.findByUserNameAndPassword(customer);
			Account account = bankCustomerService.findByCustId(cust.getCustomerId());
			request.getSession().setAttribute("customer", cust);
			request.getSession().setAttribute("account", account);
		} catch (Exception e) {
			model.addAttribute("error", "Invalid username or password");
			return "login";
		}

		return "account";
	}

	@GetMapping("/balance")
	public String showBalanceForm(HttpServletRequest request, Model model) {

		Customer customer = (Customer) request.getSession().getAttribute("customer");
		Account account = (Account) request.getSession().getAttribute("account");
		model.addAttribute("userName", customer.getUserName());
		model.addAttribute("balance", bankCustomerService.getBalance(account.getAccountNumber()));
		model.addAttribute("accountNumber", account.getAccountNumber());
		return "balance";
	}

	@PostMapping("/withdraw")
	public String withdraw(@ModelAttribute("amount") Double amount, Model model, HttpServletRequest request) throws GlobalException {

		try {
			model.addAttribute("viewName", "withdraw");
			Account account = (Account) request.getSession().getAttribute("account");
			Long accountNumbe = account.getAccountNumber();
			System.out.println("accountNumber : " + accountNumbe);
			ResponseEntity<Account> responseEntity = bankCustomerService.withdraw(accountNumbe, amount);
			if(responseEntity.getStatusCode().is2xxSuccessful())
				request.getSession().setAttribute("account", account);
			
		} catch (HttpClientErrorException ex) {
			ErrorResponse errorResponse = ex.getResponseBodyAs(ErrorResponse.class);
			throw new GlobalException(errorResponse.getMessage(), "withdraw");
		} 

		return "success";
	}

	@PostMapping("/deposit")
	public String deposit(@ModelAttribute("amount") Double amount, Model model, HttpServletRequest request) throws GlobalException {

		try {
			Account account = (Account) request.getSession().getAttribute("account");
			Long accountNumbe = account.getAccountNumber();
			System.out.println("accountNumber : " + accountNumbe);
			account = bankCustomerService.deposit(accountNumbe, amount);
			request.getSession().setAttribute("account", account);
			model.addAttribute("transaction", "Deposit");
		} catch (HttpClientErrorException ex) {
			ErrorResponse errorResponse = ex.getResponseBodyAs(ErrorResponse.class);
			throw new GlobalException(errorResponse.getMessage(), "deposit");
		} 

		return "success";
	}

	@PostMapping("/transfer")
	public String transfer(@ModelAttribute("destAccNum") Long destAccNum, @ModelAttribute("amount") Double amount,
			Model model, HttpServletRequest request) throws GlobalException {

		try {
			Account account = (Account) request.getSession().getAttribute("account");
			Long accountNumbe = account.getAccountNumber();
			System.out.println("accountNumber : " + accountNumbe);
			System.out.println("destAccNum : " + destAccNum + " amount : " + amount);
			account = bankCustomerService.transfer(accountNumbe, destAccNum, amount);
			request.getSession().setAttribute("account", account);
			model.addAttribute("transaction", "Transfered");

		} catch (HttpClientErrorException ex) {
			ErrorResponse errorResponse = ex.getResponseBodyAs(ErrorResponse.class);
			throw new GlobalException(errorResponse.getMessage(), "transfer");
		} 

		return "success";
	}

	@GetMapping("/transactions")
	public String displayLastTenTransactions(Model model, HttpServletRequest request) {

		try {
			Account account = (Account) request.getSession().getAttribute("account");
			Long accountNumbe = account.getAccountNumber();
			System.out.println("accountNumber in transactions : " + accountNumbe);
			ResponseEntity<List<Transaction>> responseEntity = bankCustomerService.getTransactions(accountNumbe);
			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				List<Transaction> transactions = responseEntity.getBody();
				model.addAttribute("transactionsList", transactions);
				model.addAttribute("accountNumber", accountNumbe);
				System.out.println("accountNumber in all transactions : " + transactions);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return "exception";
		}

		return "transactions";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		// Clear session and redirect to login page
		request.getSession().invalidate();
		return "redirect:/customer/login?logout";
	}

	@GetMapping("/withdraw")
	public String withdraw() {
		return "withdraw";
	}

	@GetMapping("/deposit")
	public String deposit() {
		return "deposit";
	}

	@GetMapping("/transfer")
	public String transfer() {
		return "transfer";
	}

	@GetMapping("/register")
	public String showRegisterForm() {
		return "register";
	}

	@GetMapping("/account")
	public String showMainPage() {
		return "account";
	}

	@GetMapping("/login")
	public String showLoginForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "login";
	}

	@GetMapping("/operations")
	public String showOperations() {
		return "operations";
	}
	
}
