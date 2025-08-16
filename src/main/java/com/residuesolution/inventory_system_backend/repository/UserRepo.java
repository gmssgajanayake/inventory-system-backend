package com.residuesolution.inventory_system_backend.repository;


import com.residuesolution.inventory_system_backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

}
