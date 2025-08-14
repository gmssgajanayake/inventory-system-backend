package com.residuesolution.inventory_system_backend.api;

import com.residuesolution.inventory_system_backend.util.StanderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    public ResponseEntity<StanderResponse> getAllUsers(){
        return null ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<StanderResponse> updateUserRoleById(@RequestParam int id){
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StanderResponse> deleteUserById(@RequestParam int id){
        return null;
    }

}
