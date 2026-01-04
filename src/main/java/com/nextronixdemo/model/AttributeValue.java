package com.nextronixdemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "attribute_values"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttributeValue {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK to Attribute
    @Column
    private Long attributeId;

    // Example: Black, Blue, 8GB, 128GB
    @Column
    private String value;
 
}
