package com.cg.bms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BankAppConfig {

	public BankAppConfig() {
		super();
	}

    // RestTemplate bean definition
    @Bean
    RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
