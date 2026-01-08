package com.nextronixdemo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.nextronixdemo.dto.ProductSpecificationRequestDto;
import com.nextronixdemo.dto.ProductSpecificationResponseDto;
import com.nextronixdemo.service.ProductSpecificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products/{productId}/specifications")
@RequiredArgsConstructor
public class ProductSpecificationController {

    private final ProductSpecificationService service;

    /* ================= SAVE BULK ================= */

    @PostMapping("/bulk")
    public String saveSpecifications(
            @PathVariable Long productId,
            @RequestBody List<ProductSpecificationRequestDto> dtos
    ) {
        service.saveBulk(productId, dtos);
        return "Specifications saved successfully";
    }

    /* ================= GET ================= */

    @GetMapping
    public List<ProductSpecificationResponseDto> getSpecifications(
            @PathVariable Long productId
    ) {
        return service.getSpecs(productId);
    }
}
