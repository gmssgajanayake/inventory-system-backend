package com.residuesolution.inventory_system_backend.service;

import com.residuesolution.inventory_system_backend.dto.request.item.ItemRequestDTO;
import com.residuesolution.inventory_system_backend.dto.response.item.ItemResponseDTO;
import org.springframework.stereotype.Service;


public interface ItemService {

    ItemResponseDTO addItems(ItemRequestDTO itemRequestDTO);

}
