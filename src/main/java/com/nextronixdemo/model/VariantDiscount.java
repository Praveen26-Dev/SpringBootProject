package com.nextronixdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="variant_discounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VariantDiscount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long variantId;
	
	@Enumerated(EnumType.STRING)
	private DiscountType discountType;
	
	private Double discountValue;
	private Boolean isActive=true;
}
