package com.residuesolution.inventory_system_backend.service;

import com.residuesolution.inventory_system_backend.dto.request.item.ItemRequestDTO;
import com.residuesolution.inventory_system_backend.dto.response.item.ItemResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ItemService {

    ItemResponseDTO addItems(ItemRequestDTO itemRequestDTO);

    List<ItemResponseDTO> getAllItems();

    ItemResponseDTO updateItemByID(Long id, ItemRequestDTO itemRequestDTO);

    ItemResponseDTO deleteItemById(Long id);

}
