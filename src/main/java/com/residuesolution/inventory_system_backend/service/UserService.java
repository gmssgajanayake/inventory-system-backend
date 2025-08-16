package com.residuesolution.inventory_system_backend.service;

import com.residuesolution.inventory_system_backend.dto.request.user.UserCredentialDTO;
import com.residuesolution.inventory_system_backend.dto.request.user.UserRequestDTO;
import com.residuesolution.inventory_system_backend.dto.response.user.UserResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


// Here I have userService extends UserDetailsService to provide user details for authentication and authorization
// UserDetailsService is provided by Spring Security

public interface UserService extends UserDetailsService {

    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);

    UserResponseDTO findUserByUserCredential(UserCredentialDTO userCredentialDTO);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUserByID(Long id, UserRequestDTO userRequestDTO);

    UserResponseDTO deleteUserById(Long id);

}
