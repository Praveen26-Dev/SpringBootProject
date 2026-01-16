package com.nextronixdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextronixdemo.model.OrderItem;

public interface OrderRepository extends JpaRepository<OrderItem, Long> {

	List<OrderItem> findByUserId(Long userId);


}
