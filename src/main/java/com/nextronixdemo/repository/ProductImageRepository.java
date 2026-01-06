package com.nextronixdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextronixdemo.model.ProductImage;

public interface ProductImageRepository  extends JpaRepository<ProductImage, Long>{

	List<ProductImage> findByProductIdAndVariantIdIsNull(Long productId);

	List<ProductImage> findByProductIdAndVariantId(Long productId, Long variantId);

}
