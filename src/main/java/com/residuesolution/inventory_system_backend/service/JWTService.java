package com.residuesolution.inventory_system_backend.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

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

    public String getToken(String username, Map<String, Object> claims) {
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (15 * 60 * 1000)))
                .signWith(secretKey)
                .compact();
    }

    public String getUsername(String token) {
        Claims claims = getTokenData(token);
        if (claims == null) {
            return null;
        }
        return claims.getSubject();
    }

    private Claims getTokenData(String token) {
        try {
            return Jwts
                    .parser()
                    .verifyWith(secretKey).build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            return null;
        }
    }

}
