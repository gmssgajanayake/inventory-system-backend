package com.residuesolution.inventory_system_backend.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTService {

    private final SecretKey secretKey;

    public JWTService() {
        // Here I have used a KeyGenerator to generate a secret key for HMAC SHA-256.
        try {
            secretKey = Keys.hmacShaKeyFor(
                    KeyGenerator
                            .getInstance("HmacSHA256")
                            .generateKey().getEncoded()
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize JWTService: " + e.getMessage(), e);
        }
    }


    public String getJWTToken() {
        return Jwts.builder()
                .subject("sakuja")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                .signWith(secretKey)
                .compact();
    }


    public String getUsername() {
        return Jwts
                .parser()
                .verifyWith(secretKey).build()
                .parseSignedClaims(getJWTToken())
                .getPayload()
                .getSubject();
    }


}
