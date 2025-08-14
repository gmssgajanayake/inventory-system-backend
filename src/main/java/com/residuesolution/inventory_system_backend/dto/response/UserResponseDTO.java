package com.residuesolution.inventory_system_backend.dto.response;


import com.residuesolution.inventory_system_backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String username;
    private String password;
    private Role role;
}
