package com.cg.bms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BankAppConsumerW2Phase6Application {

	public static void main(String[] args) {
		SpringApplication.run(BankAppConsumerW2Phase6Application.class, args);
	}

}
