package com.nextronixdemo.dto;

import lombok.Data;

@Data
public class ProductListItemDto {

	private Long productId;
    private String name;

    private String brand;

    private Double price;     // lowest variant price

    private String image;     // thumbnail image URL

    private Double rating;    // average rating (ex: 4.3)

}
