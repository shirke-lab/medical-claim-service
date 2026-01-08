package com.assignment.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import javax.crypto.SecretKey;

import io.jsonwebtoken.security.Keys;
@Component
public class Jwtutil {
//GENERATE TOKEN
//VALIDATE TOKEN
//EXTRACT DATA(USERNAME, EXPIRY)
// IS TOKEN EXPIRED

    private final SecretKey key;
    //= Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public Jwtutil(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    /*
public String generateToken(String username, List<String> roles) {
return Jwts.builder().setSubject(username).claim("roles", roles)

.setIssuedAt(new Date(System.currentTimeMillis())).
setExpiration(new Date(System.currentTimeMillis()+ 1000*60*200))
.signWith( key, SignatureAlgorithm.HS256).compact();
}
*/
    
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
	
return Jwts.parserBuilder()
	    .setSigningKey(key)
	    .build()
	    .parseClaimsJws(token).getBody();
}
@PostConstruct
public void checkKey() {
    System.out.println("ASSIGNMENT-SERVICE JWT SECRET LENGTH = " + key.getEncoded().length);
}
@PostConstruct
public void debugSecret() {
    System.out.println("JWT SECRET = [" + new String(key.getEncoded()) + "]");
    System.out.println("JWT SECRET LENGTH = " + key.getEncoded().length);
}

public boolean isTokenExpired(String token) {
	return getClaims(token).getExpiration().before(new Date());
}
public boolean validateToken(String token, String userName) {
	//return extractUserName(token).equals(userName) && !isTokenExpired(token);

	 return (userName.equals(extractUserName(token))&& !isTokenExpired(token));
}
public String getCurrentUSerId() {
	Authentication auth=SecurityContextHolder.getContext().getAuthentication();
	return auth.getName();
}
}
