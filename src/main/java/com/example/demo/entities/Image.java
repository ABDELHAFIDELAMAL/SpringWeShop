package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;

@Entity
@Setter @ToString @Getter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String downloadUrl ;
    private String fileName ;
    private String fileType ;
    @Lob
    private Blob image ;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product ;
}
