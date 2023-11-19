package com.project.ChatApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ChatApp.payloads.CategoryDto;
import com.project.ChatApp.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/api/create")
    ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto category){
    	System.out.println(category.toString());
        CategoryDto newCategory= categoryService.createCategory(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    ResponseEntity<List<CategoryDto>> getAllUsers(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    ResponseEntity<CategoryDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("/api/update/{id}")
    ResponseEntity<CategoryDto> updateUser(@RequestBody CategoryDto category,@PathVariable Long id){
    	System.out.print(category.toString());
        return ResponseEntity.ok(categoryService.updateCategory(category,id));
    }

    @DeleteMapping("/api/delete/{id}")
    ResponseEntity<String> deleteUser(@PathVariable Long id){
        this.categoryService.deleteCategory(id);
        return ResponseEntity.ok("User Deleted Successfully!");
    }

}
