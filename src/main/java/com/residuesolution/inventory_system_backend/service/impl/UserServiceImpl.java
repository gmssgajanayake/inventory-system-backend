package com.residuesolution.inventory_system_backend.service.impl;

import com.residuesolution.inventory_system_backend.dto.request.user.UserLoginCredentialDTO;
import com.residuesolution.inventory_system_backend.dto.request.user.UserRequestDTO;
import com.residuesolution.inventory_system_backend.dto.response.user.UserLoginResponseDTO;
import com.residuesolution.inventory_system_backend.dto.response.user.UserResponseDTO;
import com.residuesolution.inventory_system_backend.entity.UserEntity;
import com.residuesolution.inventory_system_backend.repository.UserRepo;
import com.residuesolution.inventory_system_backend.service.JWTService;
import com.residuesolution.inventory_system_backend.service.UserService;
import com.residuesolution.inventory_system_backend.util.mapper.UserMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.residuesolution.inventory_system_backend.config.permission.Role.ADMIN;
import static com.residuesolution.inventory_system_backend.config.permission.Role.USER;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private  final JWTService jwtService;

    public UserServiceImpl(UserRepo userRepo, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder, JWTService jwtService) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
        userRequestDTO.setPassword(bCryptPasswordEncoder.encode(userRequestDTO.getPassword()));
        return userMapper.toUserResponseDTO(userRepo.save(userMapper.toUserEntity(userRequestDTO)));
    }

    @Override
    public UserLoginResponseDTO userAuthentication(UserLoginCredentialDTO userLoginCredentialDTO) {

        UserEntity userEntityDetails = userRepo.findByUsername(userLoginCredentialDTO.getUsername());

        if (userEntityDetails != null &&
                bCryptPasswordEncoder.matches(userLoginCredentialDTO.getPassword(), userEntityDetails.getPassword())) {
            UserLoginResponseDTO userLoginResponseDTO = userMapper.toUserLoginResponseDTO(userEntityDetails);


            HashMap<String, Object> claims = new HashMap<>();
            claims.put("id", userEntityDetails.getId());
            claims.put("username", userLoginCredentialDTO.getUsername());
            claims.put("role", userEntityDetails.getRole().toString());

            userLoginResponseDTO.setToken(jwtService.getToken(userLoginResponseDTO.getUsername(),claims));
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
    public UserResponseDTO updateUserRoleByID(Long id, UserRequestDTO userRequestDTO) {

        return userMapper.toUserResponseDTO(userRepo.findById(id).map(
                userEntity -> {
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

    @Override
    public boolean isAdminExists() {
        return userRepo.findAll().stream().map(
                userEntity-> userEntity.getRole()
        ).anyMatch(role -> role.equals(ADMIN));
    }

    // This method for authentication and authorization
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserResponseDTO userDetails = findUserByUsername(username);

        return User.withUsername(userDetails.getUsername())
                .password(userDetails.getPassword())
                .roles(userDetails.getRole() == ADMIN ? ADMIN.name()  : USER.name())
                .build();

    }

}
