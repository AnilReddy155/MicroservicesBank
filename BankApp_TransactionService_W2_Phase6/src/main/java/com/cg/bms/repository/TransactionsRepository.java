package com.cg.bms.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.bms.entity.Transaction;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Long>{
	
	@Query("SELECT  trnx FROM Transaction trnx where trnx.accountNumber=:accountNumber order by trnx.timestamp desc")
	List<Transaction> findLast10TransactionsByAccountNumber(@Param("accountNumber") Long accountNumber, Pageable pageable);

	@Query("SELECT  trnx FROM Transaction trnx where trnx.accountNumber=:accountNumber")
	List<Transaction> findTransactionsByAccountNumber(Long accountNumber);

	@Query("SELECT  trnx FROM Transaction trnx where trnx.accountNumber=:accountNumber and trnx.type=:type")
	List<Transaction> findTransactionsByAccountNumberAndType(Long accountNumber, String type);

	@Query("SELECT  trnx FROM Transaction trnx where trnx.accountNumber=:accountNumber and trnx.status=:status")
	List<Transaction> findTransactionsByAccountNumberAndStatus(Long accountNumber, String status);
}
