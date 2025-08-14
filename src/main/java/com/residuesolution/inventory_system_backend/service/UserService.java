package com.residuesolution.inventory_system_backend.service;

import com.residuesolution.inventory_system_backend.dto.request.UserRequestDTO;
import com.residuesolution.inventory_system_backend.dto.response.UserResponseDTO;


public interface UserService {

    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO);

    public String getUser(String username, String password);

    public String updateUser(String username, String password, String newPassword);

    public String deleteUser(String username, String password);

    public String getAllUsers();

}
