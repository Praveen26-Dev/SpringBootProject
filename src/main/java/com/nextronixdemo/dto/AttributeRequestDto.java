package com.nextronixdemo.dto;

import java.util.List;

import lombok.Data;

@Data
public class AttributeRequestDto {

	// Example: Color, Size, RAM
    private String name;

    // Example: ["Black", "Blue", "Green"]
    private List<String> values;
}
