package com.residuesolution.inventory_system_backend.service;

import com.residuesolution.inventory_system_backend.dto.request.user.UserCredentialDTO;
import com.residuesolution.inventory_system_backend.dto.request.user.UserRequestDTO;
import com.residuesolution.inventory_system_backend.dto.response.user.UserResponseDTO;



public interface UserService {

    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO);

    public UserResponseDTO findUserByUserCredential(UserCredentialDTO userCredentialDTO);

    public String getUser(String username, String password);

    public String updateUser(String username, String password, String newPassword);

    public String deleteUser(String username, String password);

    public String getAllUsers();



}
