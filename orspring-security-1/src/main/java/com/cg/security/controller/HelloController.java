package com.cg.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/demo")
	public String hello() {
		return "Hello !";
	}
	
	@GetMapping("/hello")
	public String hello1() {
		return "Hello 1!";
	}

}
