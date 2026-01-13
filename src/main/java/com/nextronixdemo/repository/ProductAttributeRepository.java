package com.nextronixdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextronixdemo.model.ProductAttribute;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long>{

	void deleteByProductId(Long productId);

    List<ProductAttribute> findByProductId(Long productId);
    
}
