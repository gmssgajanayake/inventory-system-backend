package com.residuesolution.inventory_system_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class InventorySystemBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventorySystemBackendApplication.class, args);
	}

}
