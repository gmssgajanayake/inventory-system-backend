package com.residuesolution.inventory_system_backend.service;

import com.residuesolution.inventory_system_backend.dto.request.UserDto;
import org.springframework.stereotype.Service;


public interface UserService {

    public String registerUser(UserDto userDto);

    public String getUser(String username, String password);

    public String updateUser(String username, String password, String newPassword);

    public String deleteUser(String username, String password);

    public String getAllUsers();

}
