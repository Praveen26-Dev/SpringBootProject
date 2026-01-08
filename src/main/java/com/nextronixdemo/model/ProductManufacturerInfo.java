package com.nextronixdemo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_manufacturer_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductManufacturerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long productId;

    @Column(columnDefinition = "TEXT")
    private String content;
}
