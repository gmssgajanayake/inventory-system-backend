package com.residuesolution.inventory_system_backend.api;

import com.residuesolution.inventory_system_backend.dto.request.UserRequestDTO;
import com.residuesolution.inventory_system_backend.service.UserService;
import com.residuesolution.inventory_system_backend.util.StanderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<StanderResponse> register(@RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(
                new StanderResponse(
                        201,
                        " User registered successfully!",
                        userService.registerUser(userRequestDTO)
                ),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login") // (POST) http://localhost:8080/api/auth/login
    public ResponseEntity<StanderResponse> login() {
        return null;
    }

}
