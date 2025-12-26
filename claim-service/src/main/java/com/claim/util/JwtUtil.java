package com.claim.util;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	  private final SecretKey key;
	  //= Keys.secretKeyFor(SignatureAlgorithm.HS256);

    	    //Keys.secretKeyFor(SignatureAlgorithm.HS256);
	  
	    public JwtUtil(@Value("${jwt.secret}") String secret) {
	        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	    }

	  
	  
    public String extractUserName(String token) {
        return extractClaims(token).getSubject();
    }

    public List<GrantedAuthority> extractAuthorities(String token) {
        Claims claims = extractClaims(token);

        List<String> roles = claims.get("roles", List.class);

        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUserName(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody();
    }
}
