package com.assignment.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Component
public class Jwtutil {
//GENERATE TOKEN
//VALIDATE TOKEN
//EXTRACT DATA(USERNAME, EXPIRY)
// IS TOKEN EXPIRED

	private  final  SecretKey key;
	
	public Jwtutil(@Value("${jwt.secret}") String secret) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}
public String generateToken(String username, List<String> roles) {
return Jwts.builder().setSubject(username).claim("roles", roles)

.setIssuedAt(new Date(System.currentTimeMillis())).
setExpiration(new Date(System.currentTimeMillis()+ 1000*60*200))
.signWith( key, SignatureAlgorithm.HS256).compact();
}

public String extractUserName(String token) {
	
return	getClaims(token).getSubject();
}

//Extract authorities from token
public List<GrantedAuthority> extractAuthorities(String token) {
    Claims claims = getClaims(token);
    @SuppressWarnings("unchecked")
	List<String> roles = claims.get("roles", List.class);
    return roles.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
}

private Claims getClaims(String token) {
return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
}

public boolean isTokenExpired(String token) {
	return getClaims(token).getExpiration().before(new Date());
}
public boolean validateToken(String token, String userName) {
	return extractUserName(token).equals(userName) 
			&& !isTokenExpired(token);
}
}
