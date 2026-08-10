package com.example.demo.repositories;

import com.example.demo.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category , Long> {
    Category findByName(String name);
    boolean existsByName(String name);
    Optional<Category> findFirstByName(String electronics);
}
