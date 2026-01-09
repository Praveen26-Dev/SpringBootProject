package com.nextronixdemo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.nextronixdemo.dto.ProductListingPageDto;
import com.nextronixdemo.dto.ProductDetailsPageDto;
import com.nextronixdemo.service.ProductPageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/buyer/products")
@RequiredArgsConstructor
public class ProductPageController {

    private final ProductPageService service;

    @GetMapping("/cards")
    public List<ProductListingPageDto> getAllProductCards() {
        return service.getAllProductCards();
    }

//     PRODUCT DETAIL PAGE
    @GetMapping("/{productId}")
    public ProductDetailsPageDto getProductPage(
            @PathVariable Long productId
    ) {
        return service.getProductPage(productId);
    }
    
    
}
