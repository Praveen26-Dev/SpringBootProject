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
@Table(name="variant_attribute_values" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VariantAttributeValue {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long variantId;

    @Column
    private Long attributeId;

    @Column
    private Long attributeValueId;
	
}
