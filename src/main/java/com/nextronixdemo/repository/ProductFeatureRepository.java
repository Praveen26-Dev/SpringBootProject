package com.nextronixdemo.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextronixdemo.model.ProductFeature;

public interface ProductFeatureRepository extends JpaRepository<ProductFeature, Long>{

	List<ProductFeature> findByProductId(Long productId);

	void deleteByProductId(Long productId);

}
