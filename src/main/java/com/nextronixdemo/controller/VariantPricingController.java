package com.nextronixdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nextronixdemo.dto.VariantDiscountRequestDto;
import com.nextronixdemo.dto.VariantPriceRequestDto;
import com.nextronixdemo.dto.VariantPricingResponseDto;
import com.nextronixdemo.service.VariantPricingService;

@RestController
@RequestMapping("/api/variants/{variantId}/pricing")
public class VariantPricingController {

    @Autowired
    private VariantPricingService pricingService;

    /* ================= SET PRICE ================= */

    @PostMapping("/price")
    public ResponseEntity<String> setPrice(
            @PathVariable Long variantId,
            @RequestBody VariantPriceRequestDto request
    ) {
        pricingService.setPrice(variantId, request);
        return ResponseEntity.ok("Variant price set successfully");
    }

    /* ================= SET DISCOUNT ================= */

    @PostMapping("/discount")
    public ResponseEntity<String> setDiscount(
            @PathVariable Long variantId,
            @RequestBody VariantDiscountRequestDto request
    ) {
        pricingService.setDiscount(variantId, request);
        return ResponseEntity.ok("Variant discount set successfully");
    }

    /* ================= GET PRICING ================= */

    @GetMapping
    public ResponseEntity<VariantPricingResponseDto> getPricing(
            @PathVariable Long variantId
    ) {
        return ResponseEntity.ok(
                pricingService.getPricing(variantId)
        );
    }
}
