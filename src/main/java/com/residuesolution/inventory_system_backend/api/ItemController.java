package com.residuesolution.inventory_system_backend.api;


import com.residuesolution.inventory_system_backend.dto.request.ItemDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @GetMapping
    public String getAllItems() {
        System.out.println("ItemDto is working");
        return "all items";
    }

    @PostMapping("/add")
    public String addItem(@RequestBody ItemDto itemDto) {
        return "add itemDto";
    }

    //Admin can update item by id

    @PutMapping("/{id}")
    public String updateItemById(@PathVariable int id, @RequestBody ItemDto itemDto){
        return "update itemDto with id: " ;
    }

//    @PutMapping("/{id}")
//    public String updateQuantityById(@PathVariable int id, @RequestParam int quantity) {
//        return "update item with id: ";
//    }

}
