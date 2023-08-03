package com.angelopicc.saute.security;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    private static final Key JWT_SECRET = assignKey();

    private static final long JWT_EXPIRATION = 43_000_000;

    public String generateToken(Authentication auth) {

        String email = auth.getName();

        Date currentDate = new Date();


        String token = Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(new Date(currentDate.getTime() + JWT_EXPIRATION))
            .signWith(JWT_SECRET)
            .compact();
        

        return token;
    }

    public String getPrinciple(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(JWT_SECRET)
            .build()
            .parseClaimsJws(token)
            .getBody();
        
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
            .setSigningKey(JWT_SECRET)
            .build()
            .parse(token);
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        
        return true;
    }

    private static Key getKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        // SecretKey key = keyGenerator.generateKey();
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        return key;
    }

    private static Key assignKey() {
        try {
            return getKey();
        } catch(NoSuchAlgorithmException e) {
            return null;
        }
    }
}
