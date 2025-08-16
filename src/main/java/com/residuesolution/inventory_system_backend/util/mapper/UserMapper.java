package com.residuesolution.inventory_system_backend.util.mapper;

import com.residuesolution.inventory_system_backend.dto.request.user.UserRequestDTO;
import com.residuesolution.inventory_system_backend.dto.response.user.UserResponseDTO;
import com.residuesolution.inventory_system_backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserRequestDTO userRequestDTO);

    UserResponseDTO toUserResponseDTO(User user);

    List<UserResponseDTO> toUserResponseDTO(List<User> users);

}
