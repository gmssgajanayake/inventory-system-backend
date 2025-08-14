package com.residuesolution.inventory_system_backend.api;


import com.residuesolution.inventory_system_backend.dto.request.ItemRequestDTO;
import com.residuesolution.inventory_system_backend.util.StanderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @GetMapping
    public ResponseEntity<StanderResponse> getAllItems() {
        System.out.println("ItemRequestDTO is working");
        return null;
    }

    @PostMapping("/add")
    public ResponseEntity<StanderResponse> addItem(@RequestBody ItemRequestDTO itemDto) {
        return null;
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
