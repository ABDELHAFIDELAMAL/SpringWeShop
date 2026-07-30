package com.example.demo.request;

import com.example.demo.entities.Category;
import lombok.Data;

@Data
public class AddProductRequest {
    private Long id ;
    private String name ;
    private String description ;
    private Double price ;
    private String brand ;
    private int quantity ;
    private Category category ;
}
