package com.cg.bms.service;

import java.util.List;

import com.cg.bms.dto.AccountDto;
import com.cg.bms.entity.Account;
import com.cg.bms.exception.AccountException;

public interface AccountMgmService {
	
	Double getBalance(Long accountNumber) throws AccountException;
	AccountDto deposit(Long accountNumber, double amount) throws AccountException;
	AccountDto withdraw(Long accountNumber, double amount) throws AccountException;
	AccountDto transfer(Long fromAccountNumber,Long toAccountNumber, double amount) throws AccountException;
	Account getAccount(Long accountNumber) throws AccountException;
	List<AccountDto> getAllAccounts() throws AccountException;
	
	AccountDto addAccount(String accountType, double balance, Long custId) throws AccountException;
	AccountDto updateAccount(AccountDto accountDto) throws AccountException;
	AccountDto deleteAccount(Long accountNumber) throws AccountException;
	
	Account getAccountByCustId(Long custId) throws AccountException;
	
}
