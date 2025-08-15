package com.residuesolution.inventory_system_backend.api;

import com.residuesolution.inventory_system_backend.dto.request.user.UserRequestDTO;
import com.residuesolution.inventory_system_backend.dto.response.user.UserResponseDTO;
import com.residuesolution.inventory_system_backend.service.UserService;
import com.residuesolution.inventory_system_backend.util.StanderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping  // (GET) http://localhost:8080/api/users
    public ResponseEntity<StanderResponse> getAllUsers() {

        List<UserResponseDTO> usersList = userService.getAllUsers();

        return usersList == null ?

                ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(
                                StanderResponse.builder()
                                        .statusCode(204)
                                        .message("Users not registered yet!")
                                        .data(null)
                                        .build()
                        )
                :

                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(
                                StanderResponse.builder()
                                        .statusCode(200)
                                        .message("All users retrieved successfully!")
                                        .data(usersList)
                                        .build()
                        );
    }

    @PutMapping("/{id}") // (PUT) http://localhost:8080/api/users/{id}
    public ResponseEntity<StanderResponse> updateUserRoleById(@PathVariable Long id,
                                                              @RequestBody UserRequestDTO userRequestDTO) {

        UserResponseDTO updatedUserResponseDTO = userService.updateUserByID(id, userRequestDTO);

        return updatedUserResponseDTO == null ?

                ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(
                                StanderResponse.builder()
                                        .statusCode(404)
                                        .message("User not found with id: " + id)
                                        .data(null)
                                        .build()
                        )

                :

                ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        StanderResponse.builder()
                                .statusCode(200)
                                .message("User updated successfully!")
                                .data(updatedUserResponseDTO)
                                .build()
                );
    }

    @DeleteMapping("/{id}") // (DELETE) http://localhost:8080/api/users/{id}
    public ResponseEntity<StanderResponse> deleteUserById(@PathVariable Long id) {
        UserResponseDTO deletedUser = userService.deleteUserById(id);

       return deletedUser == null ?
                ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(
                                StanderResponse.builder()
                                        .statusCode(404)
                                        .message("User not found with id: " + id)
                                        .data(null)
                                        .build()
                        )
                :

                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(
                                StanderResponse.builder()
                                        .statusCode(200)
                                        .message("User deleted successfully!")
                                        .data(deletedUser)
                                        .build()
                        );

    }

}

