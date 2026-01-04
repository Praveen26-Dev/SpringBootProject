package com.nextronixdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextronixdemo.model.Attribute;

public interface AttributeRepository extends JpaRepository<Attribute, Long>{

}
