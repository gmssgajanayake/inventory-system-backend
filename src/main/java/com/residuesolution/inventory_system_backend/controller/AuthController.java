package com.residuesolution.inventory_system_backend.controller;

import com.residuesolution.inventory_system_backend.dto.request.user.UserCredentialDTO;
import com.residuesolution.inventory_system_backend.dto.request.user.UserRequestDTO;
import com.residuesolution.inventory_system_backend.dto.response.user.UserResponseDTO;
import com.residuesolution.inventory_system_backend.service.JWTService;
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

    private final JWTService jwtService;

    public AuthController(UserService userService, JWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")  // (POST) http://localhost:8080/api/auth/register
    public ResponseEntity<StanderResponse> register(@RequestBody UserRequestDTO userRequestDTO) {
        /*

        return new ResponseEntity<>(
                new StanderResponse(
                        201,
                        " UserEntity registered successfully!",
                        userService.registerUser(userRequestDTO)
                ),
                HttpStatus.CREATED
        );

         */

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        StanderResponse.builder()
                                .statusCode(201)
                                .message("UserEntity registered successfully!")
                                .data(userService.registerUser(userRequestDTO))
                                .build()
                );
    }

    @PostMapping("/login") // (POST) http://localhost:8080/api/auth/login
    public ResponseEntity<StanderResponse> login(@RequestBody UserCredentialDTO userCredentialDTO) {

        String jwtToken = jwtService.getJWTToken();
        System.out.println(jwtService.getUsername());

        UserResponseDTO foundUser = userService.findUserByUserCredential(userCredentialDTO);

        return foundUser == null ?

                ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(
                                StanderResponse.builder()
                                        .statusCode(401)
                                        .message("Invalid username or password!")
                                        .data(null).build()
                        )
                :

                ResponseEntity
                        .status(HttpStatus.FOUND)
                        .body(
                                StanderResponse.builder()
                                        .statusCode(200)
                                        .message("UserEntity logged in successfully!\n"+jwtToken)
                                        .data(
                                                foundUser
                                        ).build()
                        );
    }

}
