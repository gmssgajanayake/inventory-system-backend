package com.residuesolution.inventory_system_backend.service.impl;

import com.residuesolution.inventory_system_backend.dto.request.UserRequestDTO;
import com.residuesolution.inventory_system_backend.dto.response.UserResponseDTO;
import com.residuesolution.inventory_system_backend.repo.UserRepo;
import com.residuesolution.inventory_system_backend.service.UserService;
import com.residuesolution.inventory_system_backend.util.mapper.UserMapper;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }


    @Override
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {

        /*User user = new User();
        user.setUsername(userRequestDTO.getUserName());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole(userRequestDTO.getRole());

        User save = userRepo.save(user);

        UserResponseDTO userResponseDto = new UserResponseDTO();
        userResponseDto.setId(save.getId());
        userResponseDto.setUsername(save.getUsername());
        userResponseDto.setPassword(save.getPassword());
        userResponseDto.setRole(save.getRole());*/


        // Using UserMapper to convert UserRequestDTO to User and then to UserResponseDTO

//        User user = userMapper.toUser(userRequestDTO);
//        User savedUser = userRepo.save(user);
//        UserResponseDTO userResponseDto = userMapper.toUserResponseDTO(savedUser);

        //Simplified version using UserMapper

        return userMapper.toUserResponseDTO(userRepo.save(userMapper.toUser(userRequestDTO)));


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
