package com.nextronixdemo.dto;

import java.util.List;
import java.util.Map;

import com.nextronixdemo.dto.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsPageDto {

    private ProductResponseDto product;

    private List<CategoryResponseDto> breadcrumb;

    private List<ProductImageResponseDto> images;

    private List<VariantResponseDto> variants;

    // attributeId -> AttributeResponseDto (with values)
    private Map<Long, AttributeResponseDto> attributes;

    // variantId -> pricing
    private Map<Long, VariantPricingResponseDto> pricing;

    private List<ProductSpecificationResponseDto> specifications;

    private List<ProductFeatureResponseDto> features;

    private ProductManufacturerInfoResponseDto manufacturer;

    private Double minPrice;
    private Double maxPrice;
    private Boolean inStock;
}
