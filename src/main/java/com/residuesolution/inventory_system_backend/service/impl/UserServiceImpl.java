package com.residuesolution.inventory_system_backend.service.impl;

import com.residuesolution.inventory_system_backend.dto.request.user.UserCredentialDTO;
import com.residuesolution.inventory_system_backend.dto.request.user.UserRequestDTO;
import com.residuesolution.inventory_system_backend.dto.response.user.UserResponseDTO;
import com.residuesolution.inventory_system_backend.entity.User;
import com.residuesolution.inventory_system_backend.repo.UserRepo;
import com.residuesolution.inventory_system_backend.service.UserService;
import com.residuesolution.inventory_system_backend.util.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepo userRepo, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {

        // Encrypt the password before saving
        userRequestDTO.setPassword(bCryptPasswordEncoder.encode(userRequestDTO.getPassword()));

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
//
//        User savedUser = userRepo.save(user);
//
//        UserResponseDTO userResponseDto = userMapper.toUserResponseDTO(savedUser);

        // Simplified version using UserMapper


        //return  userResponseDto;

        return userMapper.toUserResponseDTO(userRepo.save(userMapper.toUser(userRequestDTO)));

    }

    @Override
    public UserResponseDTO findUserByUserCredential(UserCredentialDTO userCredentialDTO) {

        User userDetails = userRepo.findByUsername(userCredentialDTO.getUsername());

        if (userDetails != null && bCryptPasswordEncoder.matches(userCredentialDTO.getPassword(), userDetails.getPassword())) {
            return userMapper.toUserResponseDTO(userDetails);
        }

        return null;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> usersList = userRepo.findAll();

        if (usersList.isEmpty()) return null;

        return userMapper.toUserResponseDTO(usersList);
    }

    @Override
    public UserResponseDTO updateUserByID(Long id, UserRequestDTO userRequestDTO) {

        return userMapper.toUserResponseDTO(userRepo.findById(id).map(
                user -> {

                    user.setUsername(userRequestDTO.getUsername());
                    user.setPassword(bCryptPasswordEncoder.encode(userRequestDTO.getPassword()));
                    user.setRole(userRequestDTO.getRole());

                    // Hers I want to pass same user object to update the existing user
                    return userRepo.save(user);
                }
        ).orElse(null));

    }

    @Override
    public UserResponseDTO deleteUserById(Long id) {


        return userMapper.toUserResponseDTO(userRepo.findById(id).map(
                user -> {
                    userRepo.delete(user);
                    return user;
                }
        ).orElseThrow(() -> new RuntimeException("User not found with id: " + id)));


    }

}
