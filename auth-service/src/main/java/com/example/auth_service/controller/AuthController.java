package com.example.auth_service.controller;

import com.example.auth_service.util.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    @GetMapping("/authTest")
    public String authTest() {
        return "Auth service is running";
    }

    @GetMapping("/status")
    public String getStatus(@RequestParam("name") String name) {
        System.out.println("Auth Service received status check request for: " + name);
        return "ACTIVE";
    }


    @GetMapping("/token")
    public String generateToken(@RequestParam("name") String name) {

        return jwtTokenGenerator.generateToken(name);
    }
}