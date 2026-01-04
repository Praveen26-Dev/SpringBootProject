package com.nextronixdemo.dto;

import java.util.Map;

import lombok.Data;

@Data
public class VariantRequestDto {


    private String sku;
    private Double price;
    private Integer stock;

    /*
     * key   -> attributeId
     * value -> attributeValueId
     *
     * Example:
     * {
     *   1: 10,   // Color -> Black
     *   2: 22    // Storage -> 128GB
     * }
     */
    private Map<Long, Long> attributes;
}
