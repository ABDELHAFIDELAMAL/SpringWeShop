package com.example.demo.controllers;

import com.example.demo.entities.Category;
import com.example.demo.exception.AllreadyExistException;
import com.example.demo.response.ApiResponse;
import com.example.demo.services.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "${api.prefix}/categories")
public class CategoryController {
    private static final int INTERNAT_SERVER_ERROR = 404;
    @Autowired
    private ICategoryService categoryService;

    @GetMapping(path = "/all")
    public ResponseEntity<ApiResponse> getAllCategorie(){
        try {
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("Founded!", categories));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAT_SERVER_ERROR).body(new ApiResponse("Not Founded!" ,INTERNAT_SERVER_ERROR));
        }
    }

    @GetMapping(path = "/{id}/category")
    public ResponseEntity<ApiResponse> getCategorieById(@PathVariable Long id){
        try {
            Category category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Founded!", category));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAT_SERVER_ERROR).body(new ApiResponse("Not Founded!" ,INTERNAT_SERVER_ERROR));
        }
    }

    @GetMapping(path = "/category")
    public ResponseEntity<ApiResponse> getCategoryByName(@RequestParam String name){
        try {
            Category category = categoryService.getCategoryByName(name);
            return ResponseEntity.ok(new ApiResponse("Founded!", category));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAT_SERVER_ERROR).body(new ApiResponse("Not Founded!" ,INTERNAT_SERVER_ERROR));
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category){
        try {
            categoryService.AddCategory(category);
            return ResponseEntity.ok(new ApiResponse("Added!", category));
        } catch (AllreadyExistException e) {
            return ResponseEntity.status(INTERNAT_SERVER_ERROR).body(new ApiResponse("Faild add category!" ,e.getMessage()));
        }
    }

    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable Long id){
        try {
            categoryService.deleteCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("deleted!", id));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAT_SERVER_ERROR).body(new ApiResponse("Faild delete category!" ,e.getMessage()));
        }
    }

    @PutMapping(path = "/{id}/update")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id , @RequestBody Category category){
        try {
            categoryService.updateCategory(category , id);
            return ResponseEntity.ok(new ApiResponse("updated Seccess!", id));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAT_SERVER_ERROR).body(new ApiResponse("Faild update category!" ,e.getMessage()));
        }
    }

}
