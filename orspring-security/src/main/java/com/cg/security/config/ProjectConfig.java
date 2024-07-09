package com.cg.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig {
	
	@Bean
	UserDetailsService userDetailsService() {
		
		var userDetailsService = new InMemoryUserDetailsManager();
		
		var userDetails = User.withUsername("Anil")
		.password("12345")
		.authorities("read")
		.build();
		
		userDetailsService.createUser(userDetails);
		
		return userDetailsService;
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();   
	}

}
