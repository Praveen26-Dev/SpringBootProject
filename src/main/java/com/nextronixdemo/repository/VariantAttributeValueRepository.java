package com.nextronixdemo.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextronixdemo.model.VariantAttributeValue;

public interface VariantAttributeValueRepository extends JpaRepository<VariantAttributeValue, Long>{
	List<VariantAttributeValue> findByVariantId(Long variantId); 
}
