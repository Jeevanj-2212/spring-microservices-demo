package com.example.auth_service.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/authTest")
    public String authTest() {
        return "Auth service is running";
    }

    @GetMapping("status")
    public String getStatus(@RequestParam("name") String name) {
        return "ACTIVE";
    }
}
