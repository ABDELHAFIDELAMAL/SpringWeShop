package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter @ToString @Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name ;
    private String description ;
    private Double price ;
    private String brand ;
    private int quantity ;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Image> images ;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category ;

    public Product(String name, String description, Double price, String brand, int quantity , Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
        this.category = category ;
    }

}
