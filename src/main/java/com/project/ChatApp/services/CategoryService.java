package com.project.ChatApp.services;

import java.util.List;

import com.project.ChatApp.payloads.CategoryDto;
import org.springframework.stereotype.Service;


public interface CategoryService {
	CategoryDto createCategory(CategoryDto category);
	CategoryDto updateCategory(CategoryDto category,Long categoryId);
	CategoryDto getCategoryById(Long categoryId);
    List<CategoryDto> getAllCategories();
    void deleteCategory(Long categoryId);
}
