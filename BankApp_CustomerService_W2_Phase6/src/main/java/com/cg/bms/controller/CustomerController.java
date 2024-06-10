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

import com.cg.bms.dto.CustomerDto;
import com.cg.bms.entity.Customer;
import com.cg.bms.exception.CustomerException;
import com.cg.bms.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	// create endpoint to get all customers
	@GetMapping("/")
	public List<CustomerDto> getCustomers() {
		return customerService.fetchAllCustomers();
	}

	// create endpoint to get customer by id
	@GetMapping("/{custId}")
	public CustomerDto getCustomer(@PathVariable Long custId) throws CustomerException {
		return customerService.findByCustId(custId);
	}

	// create endpoint to get customer by username and password
	@GetMapping("/{userName}/{password}")
	public CustomerDto getCustomer(@PathVariable String userName,@PathVariable String password) throws CustomerException {
		return customerService.findByUserNameAndPassword(userName, password);
	}

	// create endpoint to add customer
	@PostMapping("/add")
	public CustomerDto addCustomer(@RequestBody Customer customer) throws CustomerException {
		return customerService.addCustomer(customer);
	}

	// create endpoint to update customer
	@PutMapping("/update")
	public CustomerDto updateCustomer(@RequestBody Customer customer) throws CustomerException {
		return customerService.addCustomer(customer);
	}

	// create endpoint to delete customer
	@DeleteMapping("/delete/{custId}")
	public void deleteCustomer(@PathVariable("custId") Long custId) throws CustomerException {
		customerService.deleteCustomer(custId);
	}
	
	@PostMapping("/login")
	public CustomerDto findByUserNameAndPassword(@RequestBody CustomerDto customerDto) throws CustomerException {
		return customerService.findByUserNameAndPassword(customerDto.getUserName(), customerDto.getPassword());
	}

}
