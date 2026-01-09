package com.nextronixdemo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.nextronixdemo.dto.ProductRequestDto;
import com.nextronixdemo.dto.ProductResponseDto;
import com.nextronixdemo.model.Product;
import com.nextronixdemo.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
	
	
	public ProductResponseDto createProduct(ProductRequestDto dto) {
		Product product =  modelMapper.map(dto, Product.class);
		
		Product savedProduct = productRepository.save(product);
		
		return modelMapper.map(savedProduct, ProductResponseDto.class);
	}
	
	public ProductResponseDto getProductBySlug(String slug) {
		
		Product product = productRepository.findBySlug(slug).orElseThrow(()->new RuntimeException("Product Not Found"));
		 
		return modelMapper.map(product, ProductResponseDto.class);
	}
	

        public ProductResponseDto getProductById(Long id) {
		
		Product product = productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
		return modelMapper.map(product, ProductResponseDto.class);
	    }
	public List<ProductResponseDto> getAllProducts() {

	    return productRepository.findAll()
	            .stream()
	            .map(product -> modelMapper.map(product, ProductResponseDto.class))
	            .toList();
	}
}
