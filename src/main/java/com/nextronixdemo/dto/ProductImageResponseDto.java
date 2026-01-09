package com.nextronixdemo.dto;

import lombok.Data;

@Data
public class ProductImageResponseDto {
 
	private Long id;
	
	private String imageUrl;
	private Boolean isPrimary;
	
}
