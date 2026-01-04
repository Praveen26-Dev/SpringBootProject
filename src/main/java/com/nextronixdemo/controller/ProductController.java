package com.nextronixdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextronixdemo.dto.ProductRequest;
import com.nextronixdemo.dto.ProductResponse;
import com.nextronixdemo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping
	public ProductResponse createProduct(@RequestBody ProductRequest dto) {
		return productService.createProduct(dto);
		
	}
	@GetMapping("/{slug}")
	public ProductResponse getProductBySlug(@PathVariable String slug) {
		return productService.getProductBySlug(slug);
		
	}
	
}
