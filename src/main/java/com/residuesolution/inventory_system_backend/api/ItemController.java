package com.residuesolution.inventory_system_backend.api;


import com.residuesolution.inventory_system_backend.dto.request.item.ItemRequestDTO;
import com.residuesolution.inventory_system_backend.dto.response.item.ItemResponseDTO;
import com.residuesolution.inventory_system_backend.service.ItemService;
import com.residuesolution.inventory_system_backend.util.StanderResponse;
import com.residuesolution.inventory_system_backend.util.mapper.ItemMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
    }

    @GetMapping // (GET) http://localhost:8080/api/items
    public ResponseEntity<StanderResponse> getAllItems() {

        List<ItemResponseDTO> allItems = itemService.getAllItems();

        return allItems == null ?
                ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(
                                StanderResponse.builder()
                                        .statusCode(204)
                                        .message("Items not added yet!")
                                        .data(null)
                                        .build()
                        )
                :
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(
                                StanderResponse.builder()
                                        .statusCode(200)
                                        .message("All items retrieved successfully!")
                                        .data(allItems)
                                        .build()
                        );
    }

    @PostMapping("/add") // (POST) http://localhost:8080/api/items/add
    public ResponseEntity<StanderResponse> addItem(@RequestBody ItemRequestDTO itemRequestDTO) {

       return itemRequestDTO.getPrice().intValue() < 0 || itemRequestDTO.getQuantity()<0 ?
                ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(
                                StanderResponse.builder()
                                        .statusCode(400)
                                        .message("Price and quantity can't be negative!")
                                        .data(null)
                                        .build()
                        )
                :
               ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        StanderResponse.builder()
                                .statusCode(200)
                                .message("Item added successfully!")
                                .data(itemService.addItems(itemRequestDTO))
                                .build()
                );
    }

    @PutMapping("/{id}") // (PUT) http://localhost:8080/api/items/{id}
    public ResponseEntity<StanderResponse> updateItemById(@PathVariable Long id, @RequestBody ItemRequestDTO itemRequestDTO){

        ItemResponseDTO updatedItem = itemService.updateItemByID(id, itemRequestDTO);

       return itemRequestDTO.getPrice().intValue() < 0 || itemRequestDTO.getQuantity()<0 ?

               ResponseEntity
                       .status(HttpStatus.BAD_REQUEST)
                       .body(
                               StanderResponse.builder()
                                       .statusCode(400)
                                       .message("Price and quantity can't be negative!")
                                       .data(null)
                                       .build()
                       )
               :

               updatedItem == null ?

                       ResponseEntity
                       .status(HttpStatus.NOT_FOUND)
                       .body(
                               StanderResponse.builder()
                                       .statusCode(404)
                                       .message("Item not found with id: " + id)
                                       .data(null)
                                       .build()
                       )
               :

               ResponseEntity
                       .status(HttpStatus.OK)
                          .body(
                                 StanderResponse.builder()
                                        .statusCode(200)
                                        .message("Item updated successfully!")
                                        .data(updatedItem)
                                        .build()
                          );

    }

    @DeleteMapping("/{id}") // (DELETE) http://localhost:8080/api/items/{id}
    public ResponseEntity<StanderResponse> deleteItemById(@PathVariable Long id){

        ItemResponseDTO itemResponseDTO = itemService.deleteItemById(id);

        return itemResponseDTO == null ?

                ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(
                                StanderResponse.builder()
                                        .statusCode(404)
                                        .message("Item not found with id: " + id)
                                        .data(null)
                                        .build()
                        )
                :

                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(
                                StanderResponse.builder()
                                        .statusCode(200)
                                        .message("Item deleted successfully!")
                                        .data(itemResponseDTO)
                                        .build()
                        );

    }

}
