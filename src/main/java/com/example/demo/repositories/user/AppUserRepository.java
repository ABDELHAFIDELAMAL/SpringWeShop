package com.example.demo.repositories.user;

import com.example.demo.entities.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser , Long> {
    AppUser findByUsername(String username);
    boolean existsByUsername(String admin1);
}
