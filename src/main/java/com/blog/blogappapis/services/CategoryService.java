package com.blog.blogappapis.services;

import java.util.List;

import com.blog.blogappapis.payloads.CategoryDto;

public interface CategoryService {

	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto , Integer categoryId);
	
	//delete
	public void deleteCategory(Integer categoryId);
	
	//getAll
	List<CategoryDto> getAllCategory();
	
	//getById
	public CategoryDto getCategory(Integer categoryId);
}
