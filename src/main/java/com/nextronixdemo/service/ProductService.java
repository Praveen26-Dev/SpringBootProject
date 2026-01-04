package com.nextronixdemo.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.nextronixdemo.dto.ProductRequest;
import com.nextronixdemo.dto.ProductResponse;
import com.nextronixdemo.model.Product;
import com.nextronixdemo.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
	
	
	public ProductResponse createProduct(ProductRequest dto) {
		Product product =  modelMapper.map(dto, Product.class);
		
		Product savedProduct = productRepository.save(product);
		
		return modelMapper.map(savedProduct, ProductResponse.class);
	}
	
	public ProductResponse getProductBySlug(String slug) {
		
		Product product = productRepository.findBySlug(slug).orElseThrow(()->new RuntimeException("Product Not Found"));
		 
		return modelMapper.map(product, ProductResponse.class);
	}
	
}
