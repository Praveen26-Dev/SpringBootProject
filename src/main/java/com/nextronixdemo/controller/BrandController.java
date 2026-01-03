package com.nextronixdemo.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextronixdemo.dto.BrandRequest;
import com.nextronixdemo.dto.BrandResponse;
import com.nextronixdemo.service.BrandService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

	private final BrandService brandService;
	
	@PostMapping("/bulk")
	public String createBrands(@RequestBody List<BrandRequest> dtos ) {
        brandService.createBrandsBulk(dtos);
        
        return "Brands Created Successfully";
    }
	
	@GetMapping
	public List<BrandResponse> getAllBrands(){
		return brandService.getAllBrands();
	}
}
