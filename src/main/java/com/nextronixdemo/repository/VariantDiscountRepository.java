package com.nextronixdemo.repository;

//import java.lang.foreign.Linker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextronixdemo.model.VariantDiscount;

public interface VariantDiscountRepository extends JpaRepository<VariantDiscount, Long>{

	Optional<VariantDiscount> findByVariantIdAndIsActiveTrue(Long variantId);
}
