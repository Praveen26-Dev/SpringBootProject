package com.nextronixdemo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_specifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSpecification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    @Column(nullable = false)
    private String specKey;

    @Column(nullable = false)
    private String specValue;
}
