package com.residuesolution.inventory_system_backend.repo;


import com.residuesolution.inventory_system_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}
