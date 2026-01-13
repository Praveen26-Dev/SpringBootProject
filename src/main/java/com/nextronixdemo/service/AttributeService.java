package com.nextronixdemo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nextronixdemo.dto.AttributeRequestDto;
import com.nextronixdemo.dto.AttributeResponseDto;
import com.nextronixdemo.dto.AttributeValueResponseDto;
import com.nextronixdemo.model.Attribute;
import com.nextronixdemo.model.AttributeValue;
import com.nextronixdemo.repository.AttributeRepository;
import com.nextronixdemo.repository.AttributeValueRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttributeService {

    private final AttributeRepository attributeRepo;
    private final AttributeValueRepository valueRepo;

    /* ================= CREATE ATTRIBUTE + VALUES ================= */

    public AttributeResponseDto createAttribute(AttributeRequestDto dto) {

        Attribute attribute = new Attribute();
        attribute.setName(dto.getName());
        attribute = attributeRepo.save(attribute);

        for (String val : dto.getValues()) {
            AttributeValue value = new AttributeValue();
            value.setAttributeId(attribute.getId());
            value.setValue(val);
            valueRepo.save(value);
        }

        return getAttribute(attribute.getId()); // return with IDs
    }

    /* ================= GET SINGLE ATTRIBUTE ================= */

    public AttributeResponseDto getAttribute(Long attributeId) {

        Attribute attribute = attributeRepo.findById(attributeId)
                .orElseThrow(() -> new RuntimeException("Attribute not found"));

        List<AttributeValueResponseDto> values =
        	    valueRepo.findByAttributeId(attributeId)
        	        .stream()
        	        .map(v -> {
        	            AttributeValueResponseDto dto = new AttributeValueResponseDto();
        	            dto.setId(v.getId());
        	            dto.setValue(v.getValue());
        	            return dto;
        	        })
        	        .collect(Collectors.toList());


        AttributeResponseDto res = new AttributeResponseDto();
        res.setId(attribute.getId());
        res.setName(attribute.getName());
        res.setValues(values);

        return res;
    }

    /* ================= GET ALL ATTRIBUTES ================= */

    public List<AttributeResponseDto> getAllAttributes() {

        return attributeRepo.findAll()
                .stream()
                .map(attr -> getAttribute(attr.getId()))
                .toList();
    }
}
