package com.residuesolution.inventory_system_backend.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private String name;
    private String description;
    private int quantity;
    private double price;

}
