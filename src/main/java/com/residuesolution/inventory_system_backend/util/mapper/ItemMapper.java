package com.residuesolution.inventory_system_backend.util.mapper;

import com.residuesolution.inventory_system_backend.dto.request.item.ItemRequestDTO;
import com.residuesolution.inventory_system_backend.dto.response.item.ItemResponseDTO;
import com.residuesolution.inventory_system_backend.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    ItemResponseDTO toItemResponseDTO(Item item);

    Item toItem(ItemRequestDTO itemRequestDTO);

    List<ItemResponseDTO> toItemResponseDTO(List<Item> items);

}
