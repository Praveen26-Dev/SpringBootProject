package com.nextronixdemo.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nextronixdemo.dto.AttributeRequestDto;
import com.nextronixdemo.dto.AttributeResponseDto;
import com.nextronixdemo.service.AttributeService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/attributes")
@RequiredArgsConstructor
public class AttributeController {

    private final AttributeService attributeService;

    /* ================= CREATE ATTRIBUTE + VALUES ================= */

    @PostMapping
    public ResponseEntity<AttributeResponseDto> createAttribute(
            @RequestBody AttributeRequestDto dto) {

        AttributeResponseDto response =
                attributeService.createAttribute(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /* ================= GET ALL ATTRIBUTES ================= */

    @GetMapping
    public ResponseEntity<List<AttributeResponseDto>> getAllAttributes() {

        List<AttributeResponseDto> attributes =
                attributeService.getAllAttributes();

        return ResponseEntity.ok(attributes);
    }

    /* ================= GET ATTRIBUTE BY ID ================= */

    @GetMapping("/{id}")
    public ResponseEntity<AttributeResponseDto> getAttributeById(
            @PathVariable Long id) {

        AttributeResponseDto attribute =
                attributeService.getAttributeById(id);

        return ResponseEntity.ok(attribute);
    }
}