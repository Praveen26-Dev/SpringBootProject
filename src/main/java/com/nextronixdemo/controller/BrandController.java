package com.nextronixdemo.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextronixdemo.dto.BrandRequestDto;
import com.nextronixdemo.dto.BrandResponseDto;
import com.nextronixdemo.service.BrandService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

	private final BrandService brandService;
	
	@PostMapping("/bulk")
	public String createBrands(@RequestBody List<BrandRequestDto> dtos ) {
        brandService.createBrandsBulk(dtos);
        
        return "Brands Created Successfully";
    }
	
	@GetMapping
	public List<BrandResponseDto> getAllBrands(){
		return brandService.getAllBrands();
	}
}
