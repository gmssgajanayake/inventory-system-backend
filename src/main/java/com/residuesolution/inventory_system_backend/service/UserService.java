package com.residuesolution.inventory_system_backend.service;

import com.residuesolution.inventory_system_backend.dto.request.user.UserLoginCredentialDTO;
import com.residuesolution.inventory_system_backend.dto.request.user.UserRequestDTO;
import com.residuesolution.inventory_system_backend.dto.response.user.UserLoginResponseDTO;
import com.residuesolution.inventory_system_backend.dto.response.user.UserResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


// Here I have userService extends UserDetailsService to provide user details for authentication and authorization
// UserDetailsService is provided by Spring Security

public interface UserService extends UserDetailsService {

    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);

    UserLoginResponseDTO userAuthentication(UserLoginCredentialDTO userLoginCredentialDTO);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUserRoleByID(Long id, UserRequestDTO userRequestDTO);

    UserResponseDTO deleteUserById(Long id);

    UserResponseDTO findUserByUsername(String username);

    boolean isAdminExists();
}
