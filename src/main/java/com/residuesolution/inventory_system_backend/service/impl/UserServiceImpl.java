package com.residuesolution.inventory_system_backend.service.impl;

import com.residuesolution.inventory_system_backend.dto.request.user.UserLoginCredentialDTO;
import com.residuesolution.inventory_system_backend.dto.request.user.UserRequestDTO;
import com.residuesolution.inventory_system_backend.dto.response.user.UserLoginResponseDTO;
import com.residuesolution.inventory_system_backend.dto.response.user.UserResponseDTO;
import com.residuesolution.inventory_system_backend.entity.UserEntity;
import com.residuesolution.inventory_system_backend.repository.UserRepo;
import com.residuesolution.inventory_system_backend.service.UserService;
import com.residuesolution.inventory_system_backend.util.mapper.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    //private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepo userRepo, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        //this.authenticationManager = authenticationManager;
    }

    @Override
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {

        // Encrypt the password before saving
        userRequestDTO.setPassword(bCryptPasswordEncoder.encode(userRequestDTO.getPassword()));

        /*UserEntity user = new UserEntity();
        user.setUsername(userRequestDTO.getUserName());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole(userRequestDTO.getRole());

        UserEntity save = userRepo.save(user);

        UserResponseDTO userResponseDto = new UserResponseDTO();
        userResponseDto.setId(save.getId());
        userResponseDto.setUsername(save.getUsername());
        userResponseDto.setPassword(save.getPassword());
        userResponseDto.setRole(save.getRole());*/


        // Using UserMapper to convert UserRequestDTO to UserEntity and then to UserResponseDTO

//        UserEntity user = userMapper.toUser(userRequestDTO);
//
//        UserEntity savedUser = userRepo.save(user);
//
//        UserResponseDTO userResponseDto = userMapper.toUserResponseDTO(savedUser);

        // Simplified version using UserMapper


        //return  userResponseDto;

        return userMapper.toUserResponseDTO(userRepo.save(userMapper.toUserEntity(userRequestDTO)));

    }

    @Override
    public UserLoginResponseDTO userAuthentication(UserLoginCredentialDTO userLoginCredentialDTO) {

        UserEntity userEntityDetails = userRepo.findByUsername(userLoginCredentialDTO.getUsername());

        if (userEntityDetails != null) {

//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                    userLoginCredentialDTO.getUsername(),userLoginCredentialDTO.getPassword()
//            ));

            UserLoginResponseDTO userLoginResponseDTO = userMapper.toUserLoginResponseDTO(userEntityDetails);
            userLoginResponseDTO.setToken("token");
            userLoginResponseDTO.setExpirDate(new Date());

            return userLoginResponseDTO;
        }


        return null;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<UserEntity> usersList = userRepo.findAll();

        if (usersList.isEmpty()) return null;

        return userMapper.toUserResponseDTO(usersList);
    }

    @Override
    public UserResponseDTO updateUserByID(Long id, UserRequestDTO userRequestDTO) {

        return userMapper.toUserResponseDTO(userRepo.findById(id).map(
                userEntity -> {

                    userEntity.setUsername(userRequestDTO.getUsername());
                    userEntity.setPassword(bCryptPasswordEncoder.encode(userRequestDTO.getPassword()));
                    userEntity.setRole(userRequestDTO.getRole());

                    // Hers I want to pass same userEntity object to update the existing userEntity
                    return userRepo.save(userEntity);
                }
        ).orElse(null));

    }

    @Override
    public UserResponseDTO deleteUserById(Long id) {


        return userMapper.toUserResponseDTO(userRepo.findById(id).map(
                userEntity -> {
                    userRepo.delete(userEntity);
                    return userEntity;
                }
        ).orElseThrow(() -> new RuntimeException("UserEntity not found with id: " + id)));


    }

    public UserResponseDTO findUserByUsername(String username) {
        return userMapper.toUserResponseDTO(userRepo.findByUsername(username));
    }

    // This method for authentication and authorization
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserResponseDTO userDetails = findUserByUsername(username);

        return User.withUsername(userDetails.getUsername())
                .password(userDetails.getPassword())
                .roles(userDetails.getRole().toString())
                .build();

    }

}
