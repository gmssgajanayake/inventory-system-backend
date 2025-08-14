package com.residuesolution.inventory_system_backend.service.impl;

import com.residuesolution.inventory_system_backend.dto.request.UserDto;
import com.residuesolution.inventory_system_backend.entity.User;
import com.residuesolution.inventory_system_backend.repo.UserRepo;
import com.residuesolution.inventory_system_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public String registerUser(UserDto userDto) {

        User user = new User();
        user.setUsername(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());

        userRepo.save(user);

        return "";
    }

    @Override
    public String getUser(String username, String password) {
        return "";
    }

    @Override
    public String updateUser(String username, String password, String newPassword) {
        return "";
    }

    @Override
    public String deleteUser(String username, String password) {
        return "";
    }

    @Override
    public String getAllUsers() {
        return "";
    }
}
