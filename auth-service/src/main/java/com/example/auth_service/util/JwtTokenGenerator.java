package com.example.auth_service.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Component // 1. Tells Spring Boot to manage this class!
public class JwtTokenGenerator {

    private final String secretKey;
    private final long tokenValidity;

    // 2. Tells Spring exactly where to look in the application.yml
    public JwtTokenGenerator(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.expiration}") long tokenValidity) {
        this.secretKey = secretKey;
        this.tokenValidity = tokenValidity;
    }

    public String generateToken(String username) {
        // Prepare the key
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        // Build and sign the token
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusMillis(tokenValidity)))
                .signWith(key)
                .compact();
    }
    @PostConstruct
    public void testTokenGeneration() {
        System.out.println("\n=======================================");
        System.out.println("====== GENERATING TEST JWT TOKEN ======");

        // Let's generate a token for you!
        String testToken = generateToken("Jeevan");

        System.out.println(testToken);
        System.out.println("=======================================\n");
    }
}