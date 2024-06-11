package com.cg.bms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BankingUserController {
	
	// create api to return home page
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
}