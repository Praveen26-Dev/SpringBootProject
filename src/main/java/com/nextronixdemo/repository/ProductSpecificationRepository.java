package com.nextronixdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextronixdemo.model.ProductSpecification;


public interface ProductSpecificationRepository
        extends JpaRepository<ProductSpecification, Long> {

    List<ProductSpecification> findByProductId(Long productId);

    void deleteByProductId(Long productId);
}
