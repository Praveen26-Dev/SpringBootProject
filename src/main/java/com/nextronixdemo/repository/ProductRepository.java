package com.nextronixdemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextronixdemo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	Optional<Product> findBySlug(String slug);

}
