package com.nextronixdemo.dto;

import java.util.Map;

import lombok.Data;

@Data
public class VariantResponseDto {

	private Long id;
    private String sku;
    private Double price;
    private Integer stock;

    /*
     * key   -> attributeId
     * value -> attributeValueId
     */
    private Map<Long, Long> attributes;
}
