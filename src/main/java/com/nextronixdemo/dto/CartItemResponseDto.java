package com.nextronixdemo.dto;

import com.nextronixdemo.model.CartItem;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemResponseDto {

    private Long id;
    private Long productId;
    private Long variantId;
    private String productName;
    private String image;
    private double price;
    private int quantity;

    public static CartItemResponseDto from(
        CartItem item,
        String productName,
        String img,
        double price
    ) {
        return new CartItemResponseDto(
            item.getId(),
            item.getProductId(),
            item.getVariantId(),
            productName,
            img,
            price,
            item.getQuantity()
        );
    }
}
