package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter @ToString @Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name ;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL )
    private List<Product> products ;

    public Category(String name) {
        this.name = name ;
    }

}
