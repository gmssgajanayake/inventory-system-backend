package com.residuesolution.inventory_system_backend.dto.response.user;

import com.residuesolution.inventory_system_backend.enums.Role;
import lombok.Data;

import java.util.Date;

@Data
public class UserLoginResponseDTO {
    private Long id;
    private  String username;
    private Role role;
    private String token;
    private Date expirDate;
}
