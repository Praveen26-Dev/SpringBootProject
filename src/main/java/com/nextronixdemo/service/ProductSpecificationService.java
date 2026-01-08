package com.nextronixdemo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nextronixdemo.dto.ProductSpecificationRequestDto;
import com.nextronixdemo.dto.ProductSpecificationResponseDto;
import com.nextronixdemo.model.ProductSpecification;
import com.nextronixdemo.repository.ProductSpecificationRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class ProductSpecificationService {

    private final ProductSpecificationRepository repository;

    /* ================= SAVE / UPDATE (BULK) ================= */

    public void saveBulk(
            Long productId,
            List<ProductSpecificationRequestDto> dtos
    ) {

        // 🔥 Replace old specs
        repository.deleteByProductId(productId);

        for (ProductSpecificationRequestDto dto : dtos) {
            ProductSpecification spec = new ProductSpecification();
            spec.setProductId(productId);
            spec.setSpecKey(dto.getSpecKey());
            spec.setSpecValue(dto.getSpecValue());
            repository.save(spec);
        }
    }

    /* ================= GET ================= */

    public List<ProductSpecificationResponseDto> getSpecs(Long productId) {

        return repository.findByProductId(productId)
                .stream()
                .map(spec -> {
                    ProductSpecificationResponseDto dto =
                            new ProductSpecificationResponseDto();
                    dto.setId(spec.getId());
                    dto.setSpecKey(spec.getSpecKey());
                    dto.setSpecValue(spec.getSpecValue());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
