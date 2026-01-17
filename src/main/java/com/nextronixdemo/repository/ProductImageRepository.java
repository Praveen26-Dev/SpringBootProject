package com.nextronixdemo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextronixdemo.model.ProductImage;

public interface ProductImageRepository  extends JpaRepository<ProductImage, Long>{

	List<ProductImage> findByProductIdAndVariantIdIsNull(Long productId);

	List<ProductImage> findByProductIdAndVariantId(Long productId, Long variantId);
    

	Optional<ProductImage> findFirstByProductId(Long productId);
}
