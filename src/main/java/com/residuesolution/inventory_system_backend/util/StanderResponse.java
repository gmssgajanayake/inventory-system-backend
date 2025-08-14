package com.residuesolution.inventory_system_backend.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;


@Data
@AllArgsConstructor
public class StanderResponse{
    private int statusCode;
    private String message;
    private Object data;
}
