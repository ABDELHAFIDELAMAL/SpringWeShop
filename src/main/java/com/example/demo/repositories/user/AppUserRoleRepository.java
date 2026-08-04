package com.example.demo.repositories.user;

import com.example.demo.entities.user.AppUserRole;
import com.example.demo.entities.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRoleRepository extends JpaRepository<AppUserRole , Long> {
    AppUserRole findByUserRole(UserRole roleName);
}
