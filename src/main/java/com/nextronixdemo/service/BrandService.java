package com.nextronixdemo.service;



import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.nextronixdemo.dto.BrandRequestDto;
import com.nextronixdemo.dto.BrandResponseDto;
import com.nextronixdemo.model.Brand;
import com.nextronixdemo.repository.BrandRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandService {
	private final ModelMapper modelMapper;
	private final BrandRepository brandRepository;

	public void createBrandsBulk(List<BrandRequestDto> dtos) {
		List<Brand> brands = dtos.stream()
                             .map( dto -> modelMapper.map(dto,Brand.class))
                             .collect(Collectors.toList());
		
		brandRepository.saveAll(brands);
		
	 }

	public List<BrandResponseDto> getAllBrands() {
		// TODO Auto-generated method stub
		return brandRepository.findAll()
				.stream()
				.map(brand -> modelMapper.map(brand, BrandResponseDto.class ))
				.collect(Collectors.toList());
	}
}
