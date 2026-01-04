package com.nextronixdemo.dto;

import java.util.List;

import lombok.Data;
@Data
public class AttributeResponseDto {
	private Long id;
	private String name;
	private List<String> values;

}
