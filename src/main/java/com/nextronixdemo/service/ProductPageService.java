package com.nextronixdemo.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nextronixdemo.dto.ProductImageResponseDto;
import com.nextronixdemo.dto.ProductListItemDto;
import com.nextronixdemo.dto.ProductPageResponseDto;
import com.nextronixdemo.model.Product;
import com.nextronixdemo.repository.BrandRepository;
import com.nextronixdemo.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductPageService {

    private final ProductRepository productRepo;
    private final BrandRepository brandRepo;
    private final CategoryService categoryService;
    private final ProductImageService imageService;
    private final VariantService variantService;
    private final ProductSpecificationService specificationService;
    private final ProductFeatureService featureService;
    private final ProductManufacturerInfoService manufacturerService;
//    private final ProductQuestionService questionService;
//    private final ProductReviewService reviewService;
    private final ProductAttributeService productAttributeService;
//    private final ProductAdditionalInfoService additionalInfoService;
//    private final ProductVideoService productVideoService;

    public ProductPageResponseDto getProductPage(Long productId) {

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductPageResponseDto res = new ProductPageResponseDto();

        // BASIC INFO
        res.setProductId(product.getId());
        res.setName(product.getName());
        res.setDescription(product.getDescription());

        // BRAND
        res.setBrandName(
                brandRepo.findById(product.getBrandId())
                        .map(b -> b.getName())
                        .orElse(null)
        );

        // CATEGORY BREADCRUMB
        res.setBreadcrumb(
                categoryService.getBreadCrumb(product.getCategoryId())
        );

        // IMAGES
        res.setImages(
                imageService.getImages(productId, null)
        );

        // VARIANTS
        res.setVariants(
                variantService.getVariants(productId)
        );

        res.setAttributes(
        	    variantService.buildVariantAttributes(productId)
        	);

        // SPECIFICATIONS
        res.setSpecifications(
        		 specificationService.getSpecs(productId)
        		);

        // FEATURES
        res.setFeatures(
                featureService.getFeatures(productId)
        );
         
        //MANUFACTURER iNFO
        res.setManufacturerInfo(
        		manufacturerService.get(productId)
        		);
     
        // PRICING 
//        res.setVariantPricingResponse(
//        		variantService.getPricing(productId)
//        );
//        // QUESTIONS
//        res.setQuestions(
//                questionService.getQnA(productId)
//        );
//
//        // REVIEWS
//        res.setReviews(
//                reviewService.getReviews(productId)
//        );
//
//        // RATING SUMMARY
//        res.setRatingSummary(
//                reviewService.getRatingSummary(productId)
//        );
//
//        // ADDITIONAL INFO
//        res.setAdditionalInfo(
//                additionalInfoService.getByProduct(productId)
//        );
//
//        // VIDEOS
//        res.setVideos(
//                productVideoService.getByProduct(productId)
//        );

        return res;
    }

    public List<ProductListItemDto> getProductListing() {

        List<Product> products = productRepo.findAll();
        List<ProductListItemDto> list = new ArrayList<>();

        for (Product p : products) {

            ProductListItemDto dto = new ProductListItemDto();

            dto.setProductId(p.getId());
            dto.setName(p.getName());

            /* -------- BRAND -------- */
            dto.setBrand(
                brandRepo.findById(p.getBrandId())
                    .map(b -> b.getName())
                    .orElse(null)
            );

            /* -------- PRICE (lowest variant price) -------- */
            dto.setPrice(
                variantService.getLowestPrice(p.getId())
            );

            /* -------- IMAGE (first product image) -------- */
            dto.setImage(
                imageService.getImages(p.getId(), null)
                    .stream()
                    .findFirst()
                    .map(ProductImageResponseDto::getImageUrl)
                    .orElse(null)
            );

            /* -------- RATING -------- */
//            dto.setRating(
//                reviewService.getAverageRating(p.getId())
//            );

            list.add(dto);
        }

        return list;
    }

}
