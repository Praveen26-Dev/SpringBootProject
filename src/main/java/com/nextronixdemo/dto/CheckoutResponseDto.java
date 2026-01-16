package com.nextronixdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckoutResponseDto {
    private Long orderId;
    private Long productId;
    private Long variantId;
    private Integer quantity;
    private Double price;
    private String status;
}
