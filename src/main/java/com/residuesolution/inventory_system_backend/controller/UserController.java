package com.residuesolution.inventory_system_backend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    public String getAllUsers(){
        return "all users";
    }

    @PutMapping("/{id}")
    public String updateUserRoleById(@RequestParam int id){
        return "user";
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@RequestParam int id){
        return "user deleted";
    }

}
