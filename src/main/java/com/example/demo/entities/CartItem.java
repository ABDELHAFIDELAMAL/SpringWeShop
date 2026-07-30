package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter @Getter @ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @ManyToOne
    @JoinColumn(name = "cartItem_id")
    private Cart cart ;
}
