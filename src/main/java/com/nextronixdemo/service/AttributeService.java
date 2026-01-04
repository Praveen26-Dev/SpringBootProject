package com.nextronixdemo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nextronixdemo.dto.AttributeRequestDto;
import com.nextronixdemo.dto.AttributeResponseDto;
import com.nextronixdemo.model.Attribute;
import com.nextronixdemo.model.AttributeValue;
import com.nextronixdemo.repository.AttributeRepository;
import com.nextronixdemo.repository.AttributeValueRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttributeService {

	private final AttributeRepository attributeRepository;
    private final AttributeValueRepository attributeValueRepository;
    
    public AttributeResponseDto createAttribute(AttributeRequestDto dto) {

        // 1️⃣ Save Attribute
        Attribute attribute = new Attribute();
        attribute.setName(dto.getName());

        attribute = attributeRepository.save(attribute);

        // 2️⃣ Save Attribute Values
        if (dto.getValues() != null && !dto.getValues().isEmpty()) {
            for (String value : dto.getValues()) {
                AttributeValue attributeValue = new AttributeValue();
                attributeValue.setAttributeId(attribute.getId());
                attributeValue.setValue(value);

                attributeValueRepository.save(attributeValue);
            }
        }

        // 3️⃣ Prepare response
        AttributeResponseDto response = new AttributeResponseDto();
        response.setId(attribute.getId());
        response.setName(attribute.getName());
        response.setValues(dto.getValues());

        return response;
    }

    /* ================= GET ALL ATTRIBUTES ================= */

    public List<AttributeResponseDto> getAllAttributes() {

        return attributeRepository.findAll()
                .stream()
                .map(attribute -> {

                    List<String> values = attributeValueRepository
                            .findByAttributeId(attribute.getId())
                            .stream()
                            .map(AttributeValue::getValue)
                            .collect(Collectors.toList());

                    AttributeResponseDto dto = new AttributeResponseDto();
                    dto.setId(attribute.getId());
                    dto.setName(attribute.getName());
                    dto.setValues(values);

                    return dto;
                })
                .collect(Collectors.toList());
    }

    /* ================= GET ATTRIBUTE BY ID ================= */

    public AttributeResponseDto getAttributeById(Long id) {

        Attribute attribute = attributeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attribute not found"));

        List<String> values = attributeValueRepository
                .findByAttributeId(attribute.getId())
                .stream()
                .map(AttributeValue::getValue)
                .collect(Collectors.toList());

        AttributeResponseDto dto = new AttributeResponseDto();
        dto.setId(attribute.getId());
        dto.setName(attribute.getName());
        dto.setValues(values);

        return dto;
    }
}
