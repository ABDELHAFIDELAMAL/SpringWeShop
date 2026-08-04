package com.example.demo.repositories.user;

import com.example.demo.entities.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser , Long> {
    AppUser findByUsername(String username);
}
