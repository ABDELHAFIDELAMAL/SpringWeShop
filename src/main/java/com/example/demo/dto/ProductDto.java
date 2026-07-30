package com.example.demo.dto;

import com.example.demo.entities.Category;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private Long id ;
    private String name ;
    private String brand ;
    private String description ;
    private int quantity ;
    private Double price ;
    private Category category ;
    private List<ImageDto> imageDtoList ;
}
