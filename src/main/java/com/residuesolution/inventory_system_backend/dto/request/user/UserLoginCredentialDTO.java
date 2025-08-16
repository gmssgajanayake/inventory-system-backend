package com.residuesolution.inventory_system_backend.dto.request.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginCredentialDTO {
    private String username;
    private String password;
}
