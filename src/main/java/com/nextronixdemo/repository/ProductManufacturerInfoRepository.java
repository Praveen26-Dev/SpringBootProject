package com.nextronixdemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextronixdemo.model.ProductManufacturerInfo;


public interface ProductManufacturerInfoRepository
        extends JpaRepository<ProductManufacturerInfo, Long> {

    Optional<ProductManufacturerInfo> findByProductId(Long productId);
}
