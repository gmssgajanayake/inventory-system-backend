package com.residuesolution.inventory_system_backend.repo;

import com.residuesolution.inventory_system_backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {

}
