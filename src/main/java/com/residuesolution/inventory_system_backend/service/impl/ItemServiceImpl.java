package com.residuesolution.inventory_system_backend.service.impl;

import com.residuesolution.inventory_system_backend.dto.request.item.ItemRequestDTO;
import com.residuesolution.inventory_system_backend.dto.response.item.ItemResponseDTO;
import com.residuesolution.inventory_system_backend.entity.ItemEntity;
import com.residuesolution.inventory_system_backend.repository.ItemRepo;
import com.residuesolution.inventory_system_backend.service.ItemService;
import com.residuesolution.inventory_system_backend.util.mapper.ItemMapper;
import org.springframework.security.core.context.SecurityContextHolder;
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
                itemRepo.save(itemMapper.toItemEntity(itemRequestDTO))
        );
    }

    @Override
    public List<ItemResponseDTO> getAllItems() {

        List<ItemEntity> allItemEntityEntities = itemRepo.findAll();

        if (allItemEntityEntities.isEmpty()) {
            return null;
        }

        return itemMapper.toItemResponseDTO(itemRepo.findAll());
    }

    @Override
    public ItemResponseDTO updateItemByID(Long id, ItemRequestDTO itemRequestDTO) {

        return itemMapper.toItemResponseDTO(

                itemRepo.findById(id).map(
                        itemEntity -> {

                            if (SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                                    .stream().anyMatch(
                                            user -> user.getAuthority().equals("ROLE_USER")
                                    )
                            ) {
                                itemEntity.setQuantity(itemRequestDTO.getQuantity());
                                return itemRepo.save(itemEntity);
                            }

                            itemEntity.setName(itemRequestDTO.getName());
                            itemEntity.setDescription(itemRequestDTO.getDescription());
                            itemEntity.setPrice(itemRequestDTO.getPrice());
                            itemEntity.setQuantity(itemRequestDTO.getQuantity());

                            return itemRepo.save(itemEntity);
                        }
                ).orElse(null)
        );

    }

    @Override
    public ItemResponseDTO deleteItemById(Long id) {

        return itemRepo.findById(id).map(
                itemEntity -> {
                    itemRepo.delete(itemEntity);
                    return itemMapper.toItemResponseDTO(itemEntity);
                }
        ).orElse(null);

    }
}
