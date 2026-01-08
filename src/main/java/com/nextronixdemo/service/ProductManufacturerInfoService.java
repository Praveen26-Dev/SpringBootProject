package com.nextronixdemo.service;

import org.springframework.stereotype.Service;

import com.nextronixdemo.model.ProductManufacturerInfo;
import com.nextronixdemo.repository.ProductManufacturerInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductManufacturerInfoService {

    private final ProductManufacturerInfoRepository repo;

    /* ================= SAVE / UPDATE ================= */

    public void save(Long productId, String content) {

        ProductManufacturerInfo info =
                repo.findByProductId(productId)
                        .orElse(new ProductManufacturerInfo());

        info.setProductId(productId);
        info.setContent(content);

        repo.save(info);
    }

    /* ================= GET ================= */

    public ProductManufacturerInfo get(Long productId) {
        return repo.findByProductId(productId).orElse(null);
    }
}
