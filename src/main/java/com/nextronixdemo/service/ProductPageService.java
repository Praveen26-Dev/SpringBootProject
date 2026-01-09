package com.nextronixdemo.service;

import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import com.nextronixdemo.dto.*;
import com.nextronixdemo.model.ProductFeature;
import com.nextronixdemo.model.ProductManufacturerInfo;
import com.nextronixdemo.service.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductPageService {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final VariantService variantService;
    private final AttributeService attributeService;
    private final VariantPricingService variantPricingService;
    private final ProductImageService productImageService;
    private final ProductSpecificationService productSpecificationService;
    private final ProductFeatureService productFeatureService;
    private final ProductManufacturerInfoService manufacturerInfoService;
    private final ModelMapper modelMapper;

    /* ===================== LISTING PAGE ===================== */
    public List<ProductListingPageDto> getAllProductCards() {

        List<ProductListingPageDto> cards = new ArrayList<>();

        // 1️⃣ Fetch all products
        List<ProductResponseDto> products =
                productService.getAllProducts(); // or getAllActiveProducts()

        for (ProductResponseDto product : products) {

            Long productId = product.getId();

            List<VariantResponseDto> variants =
                    variantService.getVariantsByProduct(productId);

            double minPrice = Double.MAX_VALUE;
            double maxPrice = 0;
            boolean inStock = false;
            boolean hasPricedVariant = false;

            for (VariantResponseDto v : variants) {

                VariantPricingResponseDto pricing;

                try {
                    pricing = variantPricingService.getPricing(v.getId());
                } catch (RuntimeException e) {
                    // ❌ price not set for this variant → skip
                    continue;
                }

                hasPricedVariant = true;

                minPrice = Math.min(minPrice, pricing.getFinalPrice());
                maxPrice = Math.max(maxPrice, pricing.getFinalPrice());

                if (v.getStock() != null && v.getStock() > 0) {
                    inStock = true;
                }
            }

            // 🚫 No priced variants → skip product from listing
            if (!hasPricedVariant) {
                continue;
            }

            String thumbnail =
                    productImageService.getImages(productId, null)
                            .stream()
                            .filter(img -> Boolean.TRUE.equals(img.getIsPrimary()))
                            .map(ProductImageResponseDto::getImageUrl)
                            .findFirst()
                            .orElse(null);

            cards.add(new ProductListingPageDto(
                    productId,
                    product.getName(),
                    thumbnail,
                    minPrice,
                    maxPrice,
                    inStock
            ));
        }

        return cards;
    }



    /* ===================== DETAIL PAGE ===================== */

    public ProductDetailsPageDto getProductPage(Long productId) {

        ProductResponseDto product =
                productService.getProductById(productId);

        List<VariantResponseDto> variants =
                variantService.getVariantsByProduct(productId);

        Map<Long, VariantPricingResponseDto> pricingMap = new HashMap<>();
        double minPrice = Double.MAX_VALUE;
        double maxPrice = 0;
        boolean inStock = false;

        for (VariantResponseDto v : variants) {

            VariantPricingResponseDto pricing =
                    variantPricingService.getPricing(v.getId());

            pricingMap.put(v.getId(), pricing);

            minPrice = Math.min(minPrice, pricing.getFinalPrice());
            maxPrice = Math.max(maxPrice, pricing.getFinalPrice());

            if (v.getStock() != null && v.getStock() > 0) {
                inStock = true;
            }
        }

        if (minPrice == Double.MAX_VALUE) minPrice = 0;

        Map<Long, AttributeResponseDto> attributes =
                buildAttributeGroups(variants);

        List<ProductFeatureResponseDto> features =
                productFeatureService.getFeatures(productId)
                        .stream()
                        .map(f -> {
                            ProductFeatureResponseDto dto =
                                    new ProductFeatureResponseDto();
                            dto.setProductId(f.getId());
                            dto.setFeature(f.getFeature());
                            return dto;
                        })
                        .collect(Collectors.toList());

        ProductManufacturerInfo manufacturerEntity =
                manufacturerInfoService.get(productId);

        ProductManufacturerInfoResponseDto manufacturer =
                manufacturerEntity == null
                        ? null
                        : modelMapper.map(
                                manufacturerEntity,
                                ProductManufacturerInfoResponseDto.class
                        );

        return new ProductDetailsPageDto(
                product,
                categoryService.getBreadCrumb(product.getCategoryId()),
                productImageService.getImages(productId, null),
                variants,
                attributes,
                pricingMap,
                productSpecificationService.getSpecs(productId),
                features,
                manufacturer,
                minPrice,
                maxPrice,
                inStock
        );
    }

    /* ===================== ATTRIBUTE GROUPING ===================== */

    private Map<Long, AttributeResponseDto> buildAttributeGroups(
            List<VariantResponseDto> variants
    ) {

        Map<Long, AttributeResponseDto> map = new LinkedHashMap<>();

        for (VariantResponseDto variant : variants) {
            if (variant.getAttributes() == null) continue;

            for (Long attributeId : variant.getAttributes().keySet()) {
                map.computeIfAbsent(
                        attributeId,
                        attributeService::getAttributeById
                );
            }
        }
        return map;
    }
}
