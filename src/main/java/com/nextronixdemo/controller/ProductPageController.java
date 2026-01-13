package com.nextronixdemo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.nextronixdemo.dto.ProductListItemDto;
import com.nextronixdemo.dto.ProductPageResponseDto;
import com.nextronixdemo.service.ProductPageService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/buyer/products")
@RequiredArgsConstructor
public class ProductPageController {

    private final ProductPageService service;

    @GetMapping("/list")
    public List<ProductListItemDto> getAllProductCards() {
        return service.getProductListing();
    }

//     PRODUCT DETAIL PAGE
    @GetMapping("/{productId}")
    public ProductPageResponseDto getProductPage(
            @PathVariable Long productId
    ) {
        return service.getProductPage(productId);
    }
    
    
}
