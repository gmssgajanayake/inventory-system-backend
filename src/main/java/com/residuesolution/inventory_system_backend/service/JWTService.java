package com.residuesolution.inventory_system_backend.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

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

    public String generateToken() {
        return Jwts.builder()
                .subject("user")
                .signWith(secretKey)
                .compact();
    }

    public String getUsername(String jwtToken) {
        try{
            return Jwts
                    .parser()
                    .verifyWith(secretKey).build()
                    .parseSignedClaims(jwtToken)
                    .getPayload()
                    .getSubject();
        }catch (Exception e) {
            return null;
        }


    }

}
