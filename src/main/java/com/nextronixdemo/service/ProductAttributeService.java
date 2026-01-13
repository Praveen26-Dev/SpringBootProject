package com.nextronixdemo.service;

import lombok.RequiredArgsConstructor;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nextronixdemo.dto.AttributeResponseDto;
import com.nextronixdemo.dto.AttributeValueResponseDto;
import com.nextronixdemo.dto.ProductAttributeRequestDto;
import com.nextronixdemo.model.Attribute;
import com.nextronixdemo.model.AttributeValue;
import com.nextronixdemo.model.ProductAttribute;
import com.nextronixdemo.repository.AttributeRepository;
import com.nextronixdemo.repository.AttributeValueRepository;
import com.nextronixdemo.repository.ProductAttributeRepository;

import jakarta.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class ProductAttributeService {

    private final ProductAttributeRepository repository;
    private final AttributeRepository attributeRepository;
    private final AttributeValueRepository attributeValueRepository;
    
    // SELLER SIDE: Assign attributes to product
    @Transactional
    public void assignAttributesToProduct(
            Long productId,
            ProductAttributeRequestDto dto
    ) {
        // Remove old mappings (edit-safe)
        repository.deleteByProductId(productId);

        // Insert new mappings
        for (Long attributeId : dto.getAttributeIds()) {
            ProductAttribute pa = new ProductAttribute();
            pa.setProductId(productId);
            pa.setAttributeId(attributeId);
            repository.save(pa);
        }
    }

    public List<AttributeResponseDto> getAttributesByProduct(Long productId) {

        List<ProductAttribute> mappings = repository.findByProductId(productId);
        if (mappings.isEmpty()) return List.of();

        List<AttributeResponseDto> response = new ArrayList<>();

        for (ProductAttribute pa : mappings) {

            Attribute attribute = attributeRepository.findById(pa.getAttributeId())
                    .orElseThrow(() -> new RuntimeException("Attribute not found"));
            List<AttributeValue> values =
                    attributeValueRepository.findByAttributeId(attribute.getId());

            AttributeResponseDto dto = new AttributeResponseDto();
            dto.setId(attribute.getId());
            dto.setName(attribute.getName());

            List<AttributeValueResponseDto> valueDtos =
                    values.stream().map(v -> {
                        AttributeValueResponseDto d = new AttributeValueResponseDto();
                        d.setId(v.getId());
                        d.setValue(v.getValue());
                        return d;
                    }).toList();

            dto.setValues(valueDtos);
            response.add(dto);;
        }

        return response;
    }

}
