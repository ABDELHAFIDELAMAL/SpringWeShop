package com.example.demo.repositories.user;

import com.example.demo.entities.user.AppUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRoleRepository extends JpaRepository<AppUserRole , Long> {
}
