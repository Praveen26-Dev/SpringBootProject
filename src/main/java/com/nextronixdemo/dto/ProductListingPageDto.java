package com.nextronixdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListingPageDto {

    private Long productId;
    private String name;
    private String thumbnailUrl;

    private Double minPrice;
    private Double maxPrice;

    private Boolean inStock;
}
