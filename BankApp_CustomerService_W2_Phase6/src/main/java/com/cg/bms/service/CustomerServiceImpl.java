package com.cg.bms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bms.dto.CustomerDto;
import com.cg.bms.entity.Customer;
import com.cg.bms.exception.CustomerException;
import com.cg.bms.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<CustomerDto> fetchAllCustomers() {
		return customerRepository.findAll().stream().map(CustomerDto::new).collect(Collectors.toList());
	}

	@Override
	public CustomerDto findByCustId(Long custId) throws CustomerException {
		return new CustomerDto(customerRepository.findById(custId)
				.orElseThrow(() -> new CustomerException("No Customer for the customer id : " + custId)));
	}

	@Override
	public CustomerDto addCustomer(Customer customer) throws CustomerException {
		return new CustomerDto(customerRepository.save(customer));
	}

	@Override
	public CustomerDto findByUserNameAndPassword(String userName, String password) throws CustomerException {
		return new CustomerDto(customerRepository.findByUserNameAndPassword(userName, password));
	}

	@Override
	public void deleteCustomer(Long custId) throws CustomerException {
        customerRepository.deleteById(custId);		
	}

}
