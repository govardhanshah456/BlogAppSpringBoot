package com.project.ChatApp.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ChatApp.entities.Category;
import com.project.ChatApp.exceptions.ResourceNotFoundException;
import com.project.ChatApp.payloads.CategoryDto;
import com.project.ChatApp.repositories.CategoryRepository;
import com.project.ChatApp.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CategoryDto createCategory(CategoryDto category) {
		// TODO Auto-generated method stub
		Category categoryDb=this.modelMapper.map(category, Category.class);
		this.categoryRepository.save(categoryDb);
		return this.modelMapper.map(categoryDb, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto category, Long categoryId) {
		System.out.print(category.toString());
		Category categoryDb=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        categoryDb.setName(category.getName());
        categoryDb.setDescription(category.getDescription());
        this.categoryRepository.save(categoryDb);
        return this.modelMapper.map(categoryDb,CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Long categoryId) {
		Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        return this.modelMapper.map(category,CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category>categories=categoryRepository.findAll();
        List<CategoryDto>categoryDto=new ArrayList<>();
        categories.forEach((category)->{
            categoryDto.add(this.modelMapper.map(category,CategoryDto.class));
        });
        return categoryDto;
	}

	@Override
	public void deleteCategory(Long categoryId) {
		Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        categoryRepository.delete(category);
	}
	
}
