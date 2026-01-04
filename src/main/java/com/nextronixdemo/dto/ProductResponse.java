package com.nextronixdemo.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductResponse {
	private Long id;
    private String name;
    private String slug;
    private String description;
    private Long categoryId;
    private Long brandId;
    private Boolean isActive;
    private LocalDateTime createdAt;
}
