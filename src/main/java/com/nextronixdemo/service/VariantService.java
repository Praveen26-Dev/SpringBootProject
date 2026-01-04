package com.nextronixdemo.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nextronixdemo.dto.VariantRequestDto;
import com.nextronixdemo.dto.VariantResponseDto;
import com.nextronixdemo.model.Variant;
import com.nextronixdemo.model.VariantAttributeValue;
import com.nextronixdemo.repository.VariantAttributeValueRepository;
import com.nextronixdemo.repository.VariantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VariantService {
	
	private final VariantRepository variantRepository;
	private final VariantAttributeValueRepository variantAttributeValueRepository;
    public VariantResponseDto createVariant(Long productId, VariantRequestDto dto) {

        // 1️⃣ Save Variant
        Variant variant = new Variant();
        variant.setProductId(productId);
        variant.setSku(dto.getSku());
        variant.setPrice(dto.getPrice());
        variant.setStock(dto.getStock());
        variant.setIsActive(true);

        variant = variantRepository.save(variant);

        // 2️⃣ Save VariantAttributeValues
        Map<Long, Long> attributes = dto.getAttributes();
        
        if (attributes != null && !attributes.isEmpty()) {
            for (Map.Entry<Long, Long> entry : attributes.entrySet()) {

                VariantAttributeValue vav = new VariantAttributeValue();
                vav.setVariantId(variant.getId());
                vav.setAttributeId(entry.getKey());
                vav.setAttributeValueId(entry.getValue());

                variantAttributeValueRepository.save(vav);
            }
        }

        // 3️⃣ Prepare Response
        VariantResponseDto response = new VariantResponseDto();
        response.setId(variant.getId());
        response.setSku(variant.getSku());
        response.setPrice(variant.getPrice());
        response.setStock(variant.getStock());
        response.setAttributes(attributes);

        return response;
    }
	
	
	public List<VariantResponseDto> getVariantsByProduct(Long productId) {
	

        List<Variant> variants = variantRepository.findByProductId(productId);
        List<VariantResponseDto> responseList = new ArrayList<>();

        for (Variant variant : variants) {

            List<VariantAttributeValue> attributeValues =
                    variantAttributeValueRepository.findByVariantId(variant.getId());

            Map<Long, Long> attributes = new HashMap<>();
            for (VariantAttributeValue vav : attributeValues) {
                attributes.put(vav.getAttributeId(), vav.getAttributeValueId());
            }

            VariantResponseDto dto = new VariantResponseDto();
            dto.setId(variant.getId());
            dto.setSku(variant.getSku());
            dto.setPrice(variant.getPrice());
            dto.setStock(variant.getStock());
//            dto.setIsActive(variant.getIsActive());
            dto.setAttributes(attributes);

            responseList.add(dto);
        }

        return responseList; 
    }

}


