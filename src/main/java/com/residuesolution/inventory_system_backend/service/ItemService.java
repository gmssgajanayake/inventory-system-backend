package com.residuesolution.inventory_system_backend.service;

import org.springframework.stereotype.Service;


public interface ItemService {

    String getAllItems();

    String addItem(String itemId);

    String updateItem(String itemId);

}
