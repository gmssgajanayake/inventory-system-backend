package com.residuesolution.inventory_system_backend.dto.response.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDTO {
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
}
