package com.cg.bms.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.bms.dto.CustomerDto;

@FeignClient(name = "customer-service")
public interface BankCustomerService {
	
	@PostMapping("/api/v1/customers/login")
	CustomerDto findByUserNameAndPassword(@RequestBody CustomerDto customerDto);

}
