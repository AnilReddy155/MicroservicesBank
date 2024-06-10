
package com.cg.bms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bms.dto.AccountDto;
import com.cg.bms.entity.Account;
import com.cg.bms.exception.AccountException;
import com.cg.bms.repository.AccountRepository;

@Service
public class AccountMgmServiceImpl implements AccountMgmService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Double getBalance(Long accountNumber) throws AccountException {
		Double balance = accountRepository.getBalance(accountNumber);
		if (balance != null)
			return balance;
		throw new AccountException("Account not found for account Number : " + accountNumber);
	}

	@Override
	@Transactional
	public AccountDto deposit(Long accountNumber, double amount) throws AccountException {
		validateAmount(amount);
		Account account = getAccount(accountNumber);
		account.setBalance(amount + account.getBalance());
		return new AccountDto(accountRepository.save(account));
	}

	private void validateAmount(double amount) throws AccountException {
		if ((amount <= 0)) {
			throw new AccountException("Amount should be more than 0 and Should not be negative number");
		}
	}

	@Override
	@Transactional
	public AccountDto withdraw(Long accountNumber, double amount) throws AccountException {
		validateAmount(amount);
		Account account = getAccount(accountNumber);
		if (amount <= account.getBalance()) {
			account.setBalance(account.getBalance() - amount);
			accountRepository.save(account);
			return new AccountDto(account);
		}

		throw new AccountException("Insufficient Balance for Account Id " + accountNumber);
	}

	@Override
	public Account getAccount(Long accountNumber) throws AccountException {
		Account account = accountRepository.findByAccountNumber(accountNumber);
		if (account != null) {
			return account;
		}
		throw new AccountException("Account not found with the account number " + accountNumber);
	}

	@Override
	public List<AccountDto> getAllAccounts() throws AccountException {
		return accountRepository.findAll().stream().map(AccountDto::new).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public AccountDto addAccount(String accountType, double balance, Long custId)
			throws AccountException {
		validateAmount(balance);
		return new AccountDto(accountRepository.save(new Account(accountType, balance, custId)));
	}

	@Override
	@Transactional
	public AccountDto transfer(Long fromAccountNumber, Long toAccountNumber, double amount)
			throws AccountException {
		validateAmount(amount);
		Account sourceAccount = getAccount(fromAccountNumber);

		Account destinationAccount = getAccount(toAccountNumber);
		if (amount <= sourceAccount.getBalance()) {
			sourceAccount.setBalance(sourceAccount.getBalance() - amount);
			destinationAccount.setBalance(amount + destinationAccount.getBalance());
			updateData(sourceAccount);
			updateData(destinationAccount);

			return new AccountDto(sourceAccount);
		}
		throw new AccountException("Insufficient Balance for Account Id " + sourceAccount.getAccountNumber());
	}

	private void updateData(Account account) throws AccountException {
		accountRepository.save(account);
	}

	@Override
	public AccountDto updateAccount(AccountDto accountDto) throws AccountException {
		return new AccountDto(accountRepository.save(new Account(accountDto)));
	}

	@Override
	public AccountDto deleteAccount(Long accountNumber) throws AccountException {
		return accountRepository.deleteByAccountNumber(accountNumber);
	}

	@Override
	public Account getAccountByCustId(Long custId) throws AccountException {
		return accountRepository.findByCustomerId(custId);
	}

}
