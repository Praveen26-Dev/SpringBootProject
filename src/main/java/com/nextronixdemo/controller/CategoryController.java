package com.nextronixdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextronixdemo.dto.CategoryRequest;
import com.nextronixdemo.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/bulk")
	public String createCategories(@RequestBody List<CategoryRequest> dtos) {
		
		categoryService.createCategoriesBulk(dtos);
		return "Categories Created successfully";
	}
}
