package com.cg.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.security.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long>{
	
	Customer findByEmail(String email);

}
