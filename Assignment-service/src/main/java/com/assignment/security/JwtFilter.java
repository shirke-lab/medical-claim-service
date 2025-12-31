package com.assignment.security;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.assignment.util.Jwtutil;
@Component
public class JwtFilter extends OncePerRequestFilter {
@Autowired
	private Jwtutil jwtutil;


protected void doFilterInternal(HttpServletRequest request,
								HttpServletResponse response,
								FilterChain filterchain)throws ServletException, IOException{

String authHeader=request.getHeader("Authorization");
String token=null;
String userName=null;

if(authHeader != null && authHeader.startsWith("Bearer ")) {
	token=authHeader.substring(7);
	userName=jwtutil.extractUserName(token);

	if(userName != null &&
			SecurityContextHolder.getContext().getAuthentication()==null && jwtutil.validateToken(token, userName)) {
		List <GrantedAuthority> authorities= jwtutil.extractAuthorities(token);
		
		UsernamePasswordAuthenticationToken authentication=
				new UsernamePasswordAuthenticationToken(userName, null, authorities);
	
	SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
filterchain.doFilter(request,response);
}	
	}
		

