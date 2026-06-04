package com.example.welcome_service.FiegnClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="auth-service",configuration = FeignClientInterceptor.class)
public interface AuthClient {

@GetMapping("/auth/status")
     String getStatus(@RequestParam("name") String name);

}
