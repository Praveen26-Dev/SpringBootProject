package com.nextronixdemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextronixdemo.model.VariantPrice;


public interface VariantPriceRepository extends JpaRepository<VariantPrice,Long>{
 
	Optional<VariantPrice>  findByVariantId(Long variantId);
	
}
