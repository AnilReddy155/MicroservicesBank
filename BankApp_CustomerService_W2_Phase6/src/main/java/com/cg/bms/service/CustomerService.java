package com.cg.bms.service;

import java.util.List;

import com.cg.bms.dto.CustomerDto;
import com.cg.bms.entity.Customer;
import com.cg.bms.exception.CustomerException;

public interface CustomerService {
	List<CustomerDto> fetchAllCustomers();

	CustomerDto findByCustId(Long custId) throws CustomerException;

	CustomerDto addCustomer(Customer customer) throws CustomerException;
	
	CustomerDto findByUserNameAndPassword(String userName, String password) throws CustomerException;
	
	void deleteCustomer(Long custId) throws CustomerException;
}
