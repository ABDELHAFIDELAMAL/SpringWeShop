package com.example.demo.services.product;

import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import com.example.demo.exception.ProductNotFountException;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.request.AddProductRequest;
import com.example.demo.request.UpdateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product getProductById(Long id) {
        System.out.println("Getting Product By Id : " + id);
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFountException("Product not found with id = " + id));
    }

    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id).ifPresentOrElse(
                productRepository::delete,
                () -> { throw new ProductNotFountException("Product not found with id = " + id); }
        );
    }

    @Override
    public Product updateProduct(UpdateProductRequest product, Long id) {
        return productRepository.findById(id)
                .map(existingProduct -> updateExistingProduct(existingProduct, product))
                .map(productRepository::save)
                .orElseThrow(() -> new ProductNotFountException("Product not found with id : " + id));
    }

    private Product updateExistingProduct(Product existingProduct, UpdateProductRequest request) {
        // نحدث جميع الحقول باستثناء الـ ID
        existingProduct.setName(request.getName());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setQuantity(request.getQuantity());

        // التحقق من الفئة وإذا لم تكن موجودة ننشئ واحدة جديدة
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> categoryRepository.save(new Category(request.getCategory().getName())));

        existingProduct.setCategory(category);

        return existingProduct;
    }

    @Override
    public Product addProduct(AddProductRequest product) {
        Category category = Optional.ofNullable(categoryRepository.findByName(product.getCategory().getName()))
                .orElseGet(() -> categoryRepository.save(new Category(product.getCategory().getName())));

        product.setCategory(category);
        return productRepository.save(createProduct(product, category));
    }

    private Product createProduct(AddProductRequest productRequest, Category category) {
        return new Product(
                productRequest.getName(),
                productRequest.getDescription(),
                productRequest.getPrice(),
                productRequest.getBrand(),
                productRequest.getQuantity(),
                category
        );
    }

    @Override
    public int countProductByCategory(String category) {
        return Math.toIntExact(productRepository.countByCategoryName(category));
    }
}