package com.residuesolution.inventory_system_backend.service.impl;

import com.residuesolution.inventory_system_backend.dto.request.item.ItemRequestDTO;
import com.residuesolution.inventory_system_backend.dto.response.item.ItemResponseDTO;
import com.residuesolution.inventory_system_backend.repo.ItemRepo;
import com.residuesolution.inventory_system_backend.service.ItemService;
import com.residuesolution.inventory_system_backend.util.mapper.ItemMapper;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemMapper itemMapper;
    private final ItemRepo itemRepo;

    public ItemServiceImpl(ItemMapper itemMapper, ItemRepo itemRepo) {
        this.itemMapper = itemMapper;
        this.itemRepo = itemRepo;
    }


    @Override
    public ItemResponseDTO addItems(ItemRequestDTO itemRequestDTO) {
        return itemMapper.toItemResponseDTO(
                itemRepo.save(itemMapper.toItem(itemRequestDTO))
        );
    }
}
