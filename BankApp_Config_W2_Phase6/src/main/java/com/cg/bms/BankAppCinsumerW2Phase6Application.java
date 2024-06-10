package com.cg.bms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class BankAppCinsumerW2Phase6Application {

	public static void main(String[] args) {
		SpringApplication.run(BankAppCinsumerW2Phase6Application.class, args);
	}

}
