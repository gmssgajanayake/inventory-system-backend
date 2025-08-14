package com.residuesolution.inventory_system_backend.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder // Using this I can avoid new keyword when creating an instance of this class
@Data
@AllArgsConstructor
public class StanderResponse{
    public int statusCode;
    private String message;
    private Object data;
}
