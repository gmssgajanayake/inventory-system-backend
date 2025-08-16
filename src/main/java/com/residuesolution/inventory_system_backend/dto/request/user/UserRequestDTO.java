package com.residuesolution.inventory_system_backend.dto.request.user;


import com.residuesolution.inventory_system_backend.config.permission.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private String username;
    private String password;
    private Role role;
}
