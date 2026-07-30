package com.example.demo.services.product;

import com.example.demo.entities.Product;
import com.example.demo.request.AddProductRequest;
import com.example.demo.request.UpdateProductRequest;

import java.util.List;

public interface IProductService {
    // 1. Get Single Product
    Product getProductById(Long id);

    // 2. Get Lists of Products (تم تحويل المخرجات لـ List لضمان الأمان 🎯)
    List<Product> getProductByName(String name);
    List<Product> getProductByBrand(String brand);
    List<Product> getProductByCategory(String category);
    List<Product> getProductByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByBrandAndName(String brand, String name);
    List<Product> getAllProducts();

    // 3. Operations (Add, Update, Delete)
    Product addProduct(AddProductRequest product);
    Product updateProduct(UpdateProductRequest product, Long id);
    void deleteProductById(Long id);

    // 4. Aggregations
    int countProductByCategory(String category);
}