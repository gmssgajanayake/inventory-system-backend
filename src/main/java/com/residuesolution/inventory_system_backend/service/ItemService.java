package com.residuesolution.inventory_system_backend.service;

import org.springframework.stereotype.Service;


public interface ItemService {

    public String getAllItems();

    public String addItem(String itemId);

    public String updateItem(String itemId);

}
