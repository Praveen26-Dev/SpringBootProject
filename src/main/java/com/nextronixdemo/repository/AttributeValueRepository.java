package com.nextronixdemo.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextronixdemo.model.AttributeValue;

public interface AttributeValueRepository extends JpaRepository<AttributeValue,Long> {

	List<AttributeValue> findByAttributeId(Long id);

}
