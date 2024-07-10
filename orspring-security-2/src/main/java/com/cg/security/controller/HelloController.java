package com.cg.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.security.entity.Customer;
import com.cg.security.repository.CustomerRepo;

@RestController
public class HelloController {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/demo")
	public String hello() {
		return "Hello !";
	}
	
	@GetMapping("/hello")
	public String hello1() {
		return "Hello 1!";
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<String> registerCustomer(@RequestBody Customer customer){
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		customerRepo.save(customer);
		
		return ResponseEntity.ok("User Resistered Successfully...");
	}

}
