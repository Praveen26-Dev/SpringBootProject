package com.nextronixdemo.dto;

import lombok.Data;

@Data
public class CategoryRequest {

	private String tempId;
	private String name;
	private String slug;
	private String parentTempId;
	
}
