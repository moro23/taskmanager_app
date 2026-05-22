package com.taskmanager.security;

import com.taskmanager.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;


@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    private String jwtSecret; 

    @Value("${app.jwt.expiration-ms}")
    private long jwtExpirationMs; 

    public String generateToken(User user){
        return Jwts.builder()
               .subject(user.getEmail())
               .claim("role", user.getRole().name())
               .issuedAt(new Date())
               .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
               .signWith(getSigningKey())
               .compact(); 
    }

    public String extractEmail(String token){
        return parseClaims(token).getSubject(); 
    }

    public boolean isTokenValid(String token, User user){
        final String email = extractEmail(token); 

        return email.equals(user.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return parseClaims(token).getExpiration().before(new Date());
    }

    public Claims parseClaims(String token){
        return Jwts.parser()
               .verifyWith(getSigningKey())
               .build()
               .parseSignedClaims(token)
               .getPayload();
    }

    public SecretKey getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    
    
}
