package com.residuesolution.inventory_system_backend.dto.request;


import com.residuesolution.inventory_system_backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userName;
    private String password;
    private Role role;
}
