package com.nextronixdemo.dto;

import com.nextronixdemo.model.DiscountType;

import lombok.Data;

@Data
public class VariantDiscountRequestDto {

	private DiscountType discountType;
	private Double discountValue;
}
