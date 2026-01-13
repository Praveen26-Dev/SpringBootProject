package com.nextronixdemo.dto;

import lombok.Data;
import java.util.List;

import com.nextronixdemo.model.ProductFeature;
import com.nextronixdemo.model.ProductManufacturerInfo;
import com.nextronixdemo.model.ProductSpecification;

@Data
public class ProductPageResponseDto {

    // BASIC INFO
    private Long productId;
    private String name;
    private String description;
    private String brandName;

    // CATEGORY
    private List<CategoryResponseDto> breadcrumb;

    // IMAGES
    private List<ProductImageResponseDto> images;

    // VARIANTS
    private List<VariantResponseDto> variants;

    // 🔥 ATTRIBUTES (CRITICAL FOR VARIANT UI)
    private List<AttributeResponseDto> attributes;

    // SPECIFICATIONS
    private List<ProductSpecificationResponseDto> specifications;

    // ABOUT THIS ITEM
    private List<String> features;

    // MANUFACTURER INFOr
    private ProductManufacturerInfo manufacturerInfo;
    
    //PRICING RESPONSE
//    private VariantPricingResponseDto variantPricingResponse;

//    // Q&A
//    private List<QuestionResponseDto> questions;
//
//    // ADDITIONAL INFO
//    private List<ProductAdditionalInfo> additionalInfo;
//
//    // VIDEOS
//    private List<ProductVideo> videos;
//
//    // REVIEWS
//    private List<ProductReviewResponseDto> reviews;
//    private RatingSummaryDto ratingSummary;
}
