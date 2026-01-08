package com.nextronixdemo.controller;

import org.springframework.web.bind.annotation.*;


import com.nextronixdemo.dto.ProductManufacturerInfoRequestDto;
import com.nextronixdemo.dto.ProductManufacturerInfoResponseDto;
import com.nextronixdemo.model.ProductManufacturerInfo;
import com.nextronixdemo.service.ProductManufacturerInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/manufacture/{productId}/manufacturer-info")
@RequiredArgsConstructor
public class ProductManufacturerInfoController{

    private final ProductManufacturerInfoService service;

    /* ================= SAVE / UPDATE ================= */

    @PostMapping
    public String saveManufacturerInfo(
            @PathVariable Long productId,
            @RequestBody ProductManufacturerInfoRequestDto dto
    ) {
        service.save(productId, dto.getContent());
        return "Manufacturer info saved successfully";
    }

    /* ================= GET ================= */

    @GetMapping
    public ProductManufacturerInfoResponseDto getManufacturerInfo(
            @PathVariable Long productId
    ) {
        ProductManufacturerInfo info = service.get(productId);

        if (info == null) return null;

        ProductManufacturerInfoResponseDto dto =
                new ProductManufacturerInfoResponseDto();
        dto.setProductId(info.getProductId());
        dto.setContent(info.getContent());

        return dto;
    }
}
