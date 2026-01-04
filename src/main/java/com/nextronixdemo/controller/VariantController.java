package com.nextronixdemo.controller;

import com.nextronixdemo.dto.VariantRequestDto;
import com.nextronixdemo.dto.VariantResponseDto;
import com.nextronixdemo.service.VariantService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/{productId}/variants")
@RequiredArgsConstructor
public class VariantController {

    private final VariantService variantService;

    /* ================= CREATE VARIANT ================= */

    @PostMapping
    public ResponseEntity<VariantResponseDto> createVariant(
            @PathVariable Long productId,
            @RequestBody VariantRequestDto dto) {

        VariantResponseDto response =
                variantService.createVariant(productId, dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /* ================= GET VARIANTS BY PRODUCT ================= */

    @GetMapping
    public ResponseEntity<List<VariantResponseDto>> getVariantsByProduct(
            @PathVariable Long productId) {

        List<VariantResponseDto> variants =
                variantService.getVariantsByProduct(productId);

        return ResponseEntity.ok(variants);
    }
}
