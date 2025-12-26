package com.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthService {

	public static void main(String[] args) {
		System.out.println("request is here");
		SpringApplication.run(AuthService.class, args);
	}

}
