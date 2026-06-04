package com.example.welcome_service.controller;

import com.example.welcome_service.FiegnClient.AuthClient;
import com.example.welcome_service.Validators.JwtValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @Autowired
    private AuthClient authClient;

    @Autowired
    private JwtValidator jwtValidator;

    @GetMapping("/welcomeTest")
    public String testWelcome(){
        return "Welcome Service running";
    }

    @GetMapping("/greet")
    public ResponseEntity<String> getStatus(@RequestHeader(value = "Authorization", required = false) String authHeader) {

        // 1. Check if the token header exists
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header!");
        }

        // 2. Extract the raw token string
        String token = authHeader.substring(7);

        // 3. Let the Bouncer validate the token signature and expiration
        if (!jwtValidator.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid or expired!");
        }

        // 4. Securely extract the name embedded inside the JWT payload
        String name = jwtValidator.extractName(token);

        // 5. Fire the Feign client (Interceptor automatically attaches the token here)
        String status = authClient.getStatus(name);

        return ResponseEntity.ok("The status of " + name + " is " + status);
    }
}