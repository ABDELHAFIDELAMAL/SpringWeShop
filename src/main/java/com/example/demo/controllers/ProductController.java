package com.example.demo.controllers;

import com.example.demo.entities.Product;
import com.example.demo.exception.ProductNotFountException; // أتركها كما هي فمشروعك
import com.example.demo.request.AddProductRequest;
import com.example.demo.request.UpdateProductRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.services.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "${api.prefix}/products")
public class ProductController {

    @Autowired
    private IProductService productService;


    @GetMapping(path = "/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(new ApiResponse("Products retrieved successfully!", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error fetching products: " + e.getMessage(), null));
        }
    }

    @GetMapping(path = "/{productId}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Product found!", product));
        } catch (ProductNotFountException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping(path = "/by-name")
    public ResponseEntity<ApiResponse> getProductByName(@RequestParam String name) {
        try {
            Product product = (Product) productService.getProductByName(name);
            return ResponseEntity.ok(new ApiResponse("Product found!", product));
        } catch (ProductNotFountException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping(path = "/by-brand")
    public ResponseEntity<ApiResponse> getProductByBrand(@RequestParam String brand) {
        try {
            Product product = (Product) productService.getProductByBrand(brand);
            return ResponseEntity.ok(new ApiResponse("Product found!", product));
        } catch (ProductNotFountException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping(path = "/by-category")
    public ResponseEntity<ApiResponse> getProductByCategory(@RequestParam String category) {
        try {
            Product product = (Product) productService.getProductByCategory(category);
            return ResponseEntity.ok(new ApiResponse("Product found!", product));
        } catch (ProductNotFountException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest p) {
        try {
            productService.addProduct(p);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("Product added successfully!", p));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("Could not add product: " + e.getMessage(), null));
        }
    }


    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProductById(id);
            return ResponseEntity.ok(new ApiResponse("Deleted product with id: " + id, id));
        } catch (ProductNotFountException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping(path = "/{id}/update")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody UpdateProductRequest p, @PathVariable Long id) {
        try {
            Product updatedProduct = productService.updateProduct(p, id);
            return ResponseEntity.ok(new ApiResponse("Product updated successfully!", updatedProduct));
        } catch (ProductNotFountException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping(path = "/by/brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        try {
            List<Product> products = productService.getProductsByBrandAndName(brand, name);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("No products found for given brand and name", null));
            }
            return ResponseEntity.ok(new ApiResponse("Products retrieved!", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping(path = "/by/category-and-brand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String category, @RequestParam String brand) {
        try {
            List<Product> products = Collections.singletonList((Product) productService.getProductByCategoryAndBrand(category, brand));
            return ResponseEntity.ok(new ApiResponse("Product found!", products));
        } catch (ProductNotFountException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping(path = "/count")
    public ResponseEntity<ApiResponse> countProducts(@RequestParam String category) {
        try {
            var countProducts = productService.countProductByCategory(category);
            return ResponseEntity.ok(new ApiResponse("Product count fetched!", countProducts));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
}