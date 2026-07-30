package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Setter @Getter @ToString
@AllArgsConstructor
@NoArgsConstructor @Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private Set<CartItem> items = new HashSet<>();
}
