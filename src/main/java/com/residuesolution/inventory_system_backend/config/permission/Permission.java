package com.residuesolution.inventory_system_backend.config.permission;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    USERS_READ("users:read"),

    USER_REGISTER("user:register"),

    USER_DELETE("user:delete"),

    USER_UPDATE("user:update"),

    ITEMS_READ("item:read"),

    ITEM_ADD("item:add"),

    ITEM_DELETE("item:delete"),

    ITEM_UPDATE("item:update"),

    ITEM_QUANTITY_UPDATE("item:qut-update");

    @Getter
    private final String permission;
}
