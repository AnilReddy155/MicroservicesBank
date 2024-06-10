package com.cg.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bms.dto.AccountDto;
import com.cg.bms.entity.Account;
import com.cg.bms.exception.AccountException;
import com.cg.bms.model.DepositRequest;
import com.cg.bms.model.TransferRequest;
import com.cg.bms.model.WithdrawRequest;
import com.cg.bms.repository.AccountRepository;
import com.cg.bms.service.AccountMgmService;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

	@Autowired
	private AccountMgmService accountMgmService;
	
	@Autowired
	private AccountRepository aaccountRepository;;

	// create endpoint to get all accounts
		@GetMapping("abc")
		public Account getAccount1() {
			return aaccountRepository.getAccount(1000000000L);
		}
	// create endpoint to get all accounts
	@GetMapping("/")
	public List<AccountDto> getAccounts() throws AccountException {
		return accountMgmService.getAllAccounts();
	}

	// create endpoint to get account by id
	@GetMapping("/{accountNumber}")
	public AccountDto getAccount(@PathVariable Long accountNumber) throws AccountException {
		return new AccountDto(accountMgmService.getAccount(accountNumber));
	}
	
	@GetMapping("/customer/{custId}")
	public AccountDto getAccountByCustId(@PathVariable Long custId) throws AccountException {
		return new AccountDto(accountMgmService.getAccountByCustId(custId));
	}
	
	// create endpoint to get account balance
	@GetMapping("/{accountNumber}/balance")
	public Double getBalance(@PathVariable Long accountNumber) throws AccountException {
		return accountMgmService.getBalance(accountNumber);
	}
	// create endpoint to deposit amount
	@PostMapping("/{accountNumber}/deposit")
	public AccountDto deposit(@PathVariable Long accountNumber,@RequestBody DepositRequest depositRequest) throws AccountException {
		return accountMgmService.deposit(accountNumber, depositRequest.getAmount());
	}
	
	// create endpoint to withdraw amount
	@PostMapping("/{accountNumber}/withdraw")
	public AccountDto withdraw(@PathVariable Long accountNumber,@RequestBody WithdrawRequest withdrawRequest) throws AccountException {
		return accountMgmService.withdraw(accountNumber, withdrawRequest.getAmount());
	}
	
	// create endpoint to transfer amount
	@PostMapping("/{fromAccountNumber}/transfer")
	public AccountDto transfer(@PathVariable Long fromAccountNumber,@RequestBody TransferRequest transferRequest)
			throws AccountException {
		return accountMgmService.transfer(fromAccountNumber, transferRequest.getTargetAccount(), transferRequest.getAmount());
	}

	// create endpoint to add account
	@PostMapping("/")
	public AccountDto addAccount(@RequestBody AccountDto accountDto) throws AccountException {
		return accountMgmService.addAccount(accountDto.getAccountType(), accountDto.getBalance(), accountDto.getCustomerId());
	}
	// create endpoint to update account
	@PutMapping("/")
	public AccountDto updateAccount(@RequestBody AccountDto accountDto) throws AccountException {
		return accountMgmService.updateAccount(accountDto);
	}
	// create endpoint to delete account
	@DeleteMapping("/{accountNumber}")
	public void deleteAccount(@PathVariable Long accountNumber) throws AccountException {
		accountMgmService.deleteAccount(accountNumber);
	}
	
}
