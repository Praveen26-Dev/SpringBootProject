package com.nextronixdemo.dto;

import lombok.Data;

@Data
public class CartItemRequestDto {

	private Long userId;
	private Long productId;
	private Long variantId;
	private int quantity;
}
