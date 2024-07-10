package com.cg.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cg.security.entity.Customer;
import com.cg.security.repository.CustomerRepo;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String userName = authentication.getName();
		
		String pwd = authentication.getCredentials().toString();
		
		Customer customer = customerRepo.findByEmail(userName);
		
		if(customer != null) {
			
			if(passwordEncoder.matches(pwd, customer.getPassword())) {
				
				List<GrantedAuthority> authorities = new ArrayList<>();
				
				authorities.add(new SimpleGrantedAuthority(customer.getRole()));
				
				return new UsernamePasswordAuthenticationToken(userName, pwd, authorities);
			} else {
				throw new BadCredentialsException("Invalid pwd..");
			}
			
		} else {
			throw new BadCredentialsException("Username not found");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
