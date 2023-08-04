package com.angelopicc.saute.security;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    private static final String JWT_SECRET = "c2Znc3pmZ2JkanJqZnlqbnJzYnN0ZWJoZHpmZHpnYXJnYXJoYWhkaGdmamZrZGdmaHJ1NTQ2";

    private static final long JWT_EXPIRATION = 43_000_000;

    public String generateToken(Authentication auth) {

        String email = auth.getName();

        Date currentDate = new Date();


        String token = Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(new Date(currentDate.getTime() + JWT_EXPIRATION))
            .signWith(getKey())
            .compact();
        

        return token;
    }

    public String getPrinciple(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
        
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parse(token);
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        
        return true;
    }

    private static SecretKey getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET));
    }
}
