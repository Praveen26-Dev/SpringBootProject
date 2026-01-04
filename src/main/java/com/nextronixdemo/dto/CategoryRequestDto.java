package com.nextronixdemo.dto;

import lombok.Data;

@Data
public class CategoryRequestDto {

	private String tempId;
	private String name;
	private String slug;
	private String parentTempId;
	
}
