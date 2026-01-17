package com.nextronixdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class WishlistItemResponseDto {
	private Long id;
    private Long productId;
    private String productName;
    private String imageUrl;
}
