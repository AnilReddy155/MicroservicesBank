package com.cg.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.security.entity.Customer;
import com.cg.security.repository.CustomerRepo;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private CustomerRepo customerRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepo.findByEmail(username);
		if(customer == null) {
			throw new UsernameNotFoundException("User Not found");
		}
		return User.withUsername(customer.getEmail()).password(customer.getPassword())
		.authorities(new SimpleGrantedAuthority(customer.getRole())).build();
	}

}
