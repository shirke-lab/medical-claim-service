package com.claim.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.claim.util.JwtUtil;


@EnableMethodSecurity
@Configuration
public class SecurityConfig {
		private final JwtFilter jwtFilter;
	private final JwtUtil jwtUtil;
	
	SecurityConfig(JwtUtil jwtUtil, JwtFilter jwtFilter){
		this.jwtUtil=jwtUtil;
	this.jwtFilter=jwtFilter;
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtUtil jwtutil )throws Exception{
	
		http.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/claims/**").authenticated()
				.anyRequest().permitAll()
				).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	return http.build();
}
}
