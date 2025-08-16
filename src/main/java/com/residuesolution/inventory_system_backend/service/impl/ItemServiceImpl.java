package com.residuesolution.inventory_system_backend.service.impl;

import com.residuesolution.inventory_system_backend.dto.request.item.ItemRequestDTO;
import com.residuesolution.inventory_system_backend.dto.response.item.ItemResponseDTO;
import com.residuesolution.inventory_system_backend.entity.Item;
import com.residuesolution.inventory_system_backend.repository.ItemRepo;
import com.residuesolution.inventory_system_backend.service.ItemService;
import com.residuesolution.inventory_system_backend.util.mapper.ItemMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<ItemResponseDTO> getAllItems() {

        List<Item> allItems = itemRepo.findAll();

        if (allItems.isEmpty()) {
            return null;
        }

        return itemMapper.toItemResponseDTO(itemRepo.findAll());
    }

    @Override
    public ItemResponseDTO updateItemByID(Long id, ItemRequestDTO itemRequestDTO) {

        return itemMapper.toItemResponseDTO(

                itemRepo.findById(id).map(
                        item -> {

                            item.setName(itemRequestDTO.getName());
                            item.setDescription(itemRequestDTO.getDescription());
                            item.setPrice(itemRequestDTO.getPrice());
                            item.setQuantity(itemRequestDTO.getQuantity());

                            return itemRepo.save(item);
                        }
                ).orElse(null)
        );

    }

    @Override
    public ItemResponseDTO deleteItemById(Long id) {

        return itemRepo.findById(id).map(
                item -> {
                    itemRepo.delete(item);
                    return itemMapper.toItemResponseDTO(item);
                }
        ).orElse(null);

    }
}
