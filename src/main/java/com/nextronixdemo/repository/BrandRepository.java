package com.nextronixdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextronixdemo.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long>{

}
