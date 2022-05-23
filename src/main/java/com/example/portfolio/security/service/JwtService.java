package com.example.portfolio.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
  
  private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
  
  public String generateToken(String subject) {
    
    return Jwts.builder()
               .setId("fz811")
               .setSubject(subject)
               .setIssuedAt(new Date(System.currentTimeMillis()))
               .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)))
               .signWith(key)
               .compact();
  }
  
  public Claims getClaims(String token) {
    return Jwts.parserBuilder()
               .setSigningKey(key)
               .build()
               .parseClaimsJws(token)
               .getBody();
  }
  
  public boolean isValidToken(String token, String username) {
    String tokenUserName = getSubject(token);
    return (username.equals(tokenUserName) && !isTokenExpired(token));
  }
  
  public boolean isTokenExpired(String token) {
    return getExpirationDate(token).before(new Date(System.currentTimeMillis()));
  }
  
  public Date getExpirationDate(String token) {
    return getClaims(token).getExpiration();
  }
  
  public String getSubject(String token) {
    return getClaims(token).getSubject();
  }
}
