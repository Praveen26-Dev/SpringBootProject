package com.nextronixdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.nextronixdemo.dto.ProductAttributeRequestDto;
import com.nextronixdemo.service.ProductAttributeService;


@RestController
@RequestMapping("/api/products/{productId}/attributes")
@RequiredArgsConstructor
public class ProductAttributeController {

    private final ProductAttributeService service;

    @PostMapping
    public String assignAttributes(
            @PathVariable Long productId,
            @RequestBody ProductAttributeRequestDto dto
    ) {
        service.assignAttributesToProduct(productId, dto);
        return "Attributes assigned to product";
    }
}
