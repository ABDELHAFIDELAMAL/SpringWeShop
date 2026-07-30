package com.example.demo.services.category;

import com.example.demo.entities.Category;
import java.util.List;

public interface ICategoryService {
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category AddCategory(Category category);
    Category updateCategory(Category category , Long id);
    void deleteCategoryById(Long id);
}
