package com.residuesolution.inventory_system_backend.util;

import com.residuesolution.inventory_system_backend.dto.request.user.UserRequestDTO;
import com.residuesolution.inventory_system_backend.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.residuesolution.inventory_system_backend.config.permission.Role.ADMIN;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;

    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userService.isAdminExists()) {
            userService.registerUser(
                    new UserRequestDTO("admin", "admin123", ADMIN)
            );
        }

    }
}
