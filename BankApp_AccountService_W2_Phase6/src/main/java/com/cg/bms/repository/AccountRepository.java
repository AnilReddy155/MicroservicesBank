package com.cg.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.bms.dto.AccountDto;
import com.cg.bms.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	Account findByAccountNumber(Long accountNumber);
	@Query("select a.balance from Account a where a.accountNumber=:accountId")
	Double getBalance(Long accountId);
	AccountDto deleteByAccountNumber(Long accountNumber);
	
	Account findByCustomerId(Long customerId);
	
	//write native query to fetch the account details by account number
	@Query(value="select * from bms_acc_db.account where account_number=:accountNumber order by account_type desc",nativeQuery=true)
	Account getAccount(Long accountNumber);
}
