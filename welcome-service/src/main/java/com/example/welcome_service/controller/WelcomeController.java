package com.example.welcome_service.controller;

import com.example.welcome_service.controller.FiegnClient.AuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @Autowired
    AuthClient authClient;
     @GetMapping("/welcomeTest")
    public String testWelcome(){
        return "Welcome Service running";
    }
  @GetMapping("/greet")
    public String getStatus(@RequestParam("name") String name){
         String status = authClient.getStatus(name);

         return "The status of "+name+" is "+status;
  }
}
