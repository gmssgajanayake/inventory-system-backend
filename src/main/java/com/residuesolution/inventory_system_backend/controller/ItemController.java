package com.residuesolution.inventory_system_backend.controller;


import com.residuesolution.inventory_system_backend.model.Item;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @GetMapping
    public String getAllItems() {
        System.out.println("Item is working");
        return "all items";
    }

    @PostMapping("/add")
    public String addItem(@RequestBody Item item) {
        return "add item";
    }

    //Admin can update item by id

    @PutMapping("/{id}")
    public String updateItemById(@PathVariable int id, @RequestBody Item item){
        return "update item with id: " ;
    }

//    @PutMapping("/{id}")
//    public String updateQuantityById(@PathVariable int id, @RequestParam int quantity) {
//        return "update item with id: ";
//    }

}
