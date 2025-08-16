package com.residuesolution.inventory_system_backend.util.mapper;

import com.residuesolution.inventory_system_backend.dto.request.user.UserRequestDTO;
import com.residuesolution.inventory_system_backend.dto.response.user.UserLoginResponseDTO;
import com.residuesolution.inventory_system_backend.dto.response.user.UserResponseDTO;
import com.residuesolution.inventory_system_backend.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity toUserEntity(UserRequestDTO userRequestDTO);

    UserResponseDTO toUserResponseDTO(UserEntity userEntity);

    List<UserResponseDTO> toUserResponseDTO(List<UserEntity> userEntityEntities);

    UserLoginResponseDTO toUserLoginResponseDTO(UserEntity userEntity);

}
