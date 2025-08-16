package com.residuesolution.inventory_system_backend.util.mapper;

import com.residuesolution.inventory_system_backend.dto.request.item.ItemRequestDTO;
import com.residuesolution.inventory_system_backend.dto.response.item.ItemResponseDTO;
import com.residuesolution.inventory_system_backend.entity.ItemEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    //ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);


    ItemResponseDTO toItemResponseDTO(ItemEntity itemEntity);

    ItemEntity toItemEntity(ItemRequestDTO itemRequestDTO);

    List<ItemResponseDTO> toItemResponseDTO(List<ItemEntity> itemEntityEntities);

}
