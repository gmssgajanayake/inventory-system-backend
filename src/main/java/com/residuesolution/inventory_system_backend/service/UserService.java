package com.residuesolution.inventory_system_backend.service;

import com.residuesolution.inventory_system_backend.dto.request.user.UserCredentialDTO;
import com.residuesolution.inventory_system_backend.dto.request.user.UserRequestDTO;
import com.residuesolution.inventory_system_backend.dto.response.user.UserResponseDTO;

import java.util.List;


public interface UserService {

    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);

    UserResponseDTO findUserByUserCredential(UserCredentialDTO userCredentialDTO);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUserByID(Long id, UserRequestDTO userRequestDTO);

    UserResponseDTO deleteUserById(Long id);

    String getUser(String username, String password);

    String updateUser(String username, String password, String newPassword);

    String deleteUser(String username, String password);


}
