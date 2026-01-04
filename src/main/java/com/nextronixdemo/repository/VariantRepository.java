package com.nextronixdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextronixdemo.model.Variant;

public interface VariantRepository extends JpaRepository<Variant,Long>{
	
    List<Variant> findByProductId(Long productId);
}
