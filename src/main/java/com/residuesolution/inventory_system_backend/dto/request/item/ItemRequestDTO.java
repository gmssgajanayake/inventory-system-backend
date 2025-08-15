package com.residuesolution.inventory_system_backend.dto.request.item;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDTO {
    private String name;
    private String description;
    private int quantity;
    private double price;

}
