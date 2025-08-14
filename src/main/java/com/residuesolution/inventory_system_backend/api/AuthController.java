package com.residuesolution.inventory_system_backend.api;

import com.residuesolution.inventory_system_backend.dto.request.UserDto;
import com.residuesolution.inventory_system_backend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")  // (POST) http://localhost:8080/api/auth/register
    public String register(@RequestBody UserDto userDto) {
        userService.registerUser(userDto);
        return "User registered successfully";
    }

    @PostMapping("/login") // (POST) http://localhost:8080/api/auth/login
    public String login() {
        return "login";
    }

}
