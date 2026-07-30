package com.example.demo.services.category;

import com.example.demo.entities.Category;
import com.example.demo.exception.AllreadyExistException;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository ;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new CategoryNotFoundException("Category not found ! with id : " + id));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category AddCategory(Category category) {
        return Optional.of(category).filter(c-> !categoryRepository.existsByName(c.getName()))
                .map(categoryRepository::save)
                .orElseThrow(()-> new AllreadyExistException("Already Exist !"));
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        Category category1 = categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException("Category not found with id : " + id));
        category1.setName(category.getName());
        return categoryRepository.save(category1);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
