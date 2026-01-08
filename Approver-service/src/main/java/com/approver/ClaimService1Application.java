package com.approver;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableRabbit
@SpringBootApplication
public class ClaimService1Application {

	public static void main(String[] args) {
		SpringApplication.run(ClaimService1Application.class, args);
	}

}
