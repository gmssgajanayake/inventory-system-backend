package com.residuesolution.inventory_system_backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/login")
    public String login(){
        return "login";
    }

}
