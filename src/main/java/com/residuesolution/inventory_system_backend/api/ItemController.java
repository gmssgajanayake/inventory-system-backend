package com.residuesolution.inventory_system_backend.api;


import com.residuesolution.inventory_system_backend.dto.request.item.ItemRequestDTO;
import com.residuesolution.inventory_system_backend.service.ItemService;
import com.residuesolution.inventory_system_backend.util.StanderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<StanderResponse> getAllItems() {
        return null;
    }

    @PostMapping("/add")
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

    //Admin can update item by id

    @PutMapping("/{id}")
    public ResponseEntity<StanderResponse> updateItemById(@PathVariable int id, @RequestBody ItemRequestDTO itemDto){
        return null ;
    }

//    @PutMapping("/{id}")
//    public String updateQuantityById(@PathVariable int id, @RequestParam int quantity) {
//        return "update item with id: ";
//    }

}
