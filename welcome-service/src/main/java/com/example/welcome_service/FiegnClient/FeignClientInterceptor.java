package com.example.welcome_service.FiegnClient;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        // 1. Grab the original incoming HTTP request (the one you sent from Postman)
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();

            // 2. Extract the exact "Authorization" header
            String authorizationHeader = request.getHeader("Authorization");

            // 3. If the token exists, glue it onto the outgoing Feign request!
            if (authorizationHeader != null) {
                template.header("Authorization", authorizationHeader);
                System.out.println("Interceptor successfully attached the JWT token to the outgoing Feign request!");
            }
        }
    }
}