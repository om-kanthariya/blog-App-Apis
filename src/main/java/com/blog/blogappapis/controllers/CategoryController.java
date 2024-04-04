package com.blog.blogappapis.controllers;

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

import com.blog.blogappapis.payloads.ApiResponse;
import com.blog.blogappapis.payloads.CategoryDto;
import com.blog.blogappapis.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
		
		CategoryDto createCategory =  this.categoryService.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
		
	}
	
	//update
	@PutMapping("/{catid}")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto , @PathVariable Integer catid) {
		
		CategoryDto updateCategory =  this.categoryService.updateCategory(categoryDto,catid);
		
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
		
	}
	
	//delete
	@DeleteMapping("/{catid}")
	public ResponseEntity<ApiResponse> deleteCategory( @PathVariable Integer catid) {
		
		this.categoryService.deleteCategory(catid);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category is Deleted Successfully", true),HttpStatus.OK);
		
	}
	//get
	
	@GetMapping("/{catid}")
	public ResponseEntity<CategoryDto> getCategory( @PathVariable Integer catid) {
		CategoryDto getCategory = this.categoryService.getCategory(catid);
		return new ResponseEntity<CategoryDto>(getCategory,HttpStatus.OK);
	}
	//getAll
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		List<CategoryDto> list = this.categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(list,HttpStatus.OK);
	}
	
	
}
