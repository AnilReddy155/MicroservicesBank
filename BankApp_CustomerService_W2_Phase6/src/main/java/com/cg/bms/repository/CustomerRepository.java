package com.cg.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.bms.entity.Customer;
import com.cg.bms.exception.CustomerException;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	
	Customer findByUserNameAndPassword(String userName, String password) throws CustomerException;

}
