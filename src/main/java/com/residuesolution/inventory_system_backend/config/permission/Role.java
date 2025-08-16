package com.residuesolution.inventory_system_backend.config.permission;

import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.Set;

import static com.residuesolution.inventory_system_backend.config.permission.Permission.*;

public enum Role {


    ADMIN(Sets.newHashSet(      // Sets.newHashSet() form third party lib
            USERS_READ,USER_UPDATE,USER_REGISTER,USER_DELETE,
            ITEMS_READ,ITEM_UPDATE,ITEM_ADD,ITEM_DELETE
    )),

    USER(Sets.newHashSet(
            ITEMS_READ,ITEM_UPDATE,ITEM_QUANTITY_UPDATE
    ));

    @Getter
    private final Set<Permission> permissions;


    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
