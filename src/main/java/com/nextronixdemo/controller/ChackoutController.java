package com.nextronixdemo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextronixdemo.model.OrderItem;
import com.nextronixdemo.service.CheckoutService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
public class ChackoutController {

	private final CheckoutService checkoutService;
	
	@PostMapping("/{userId}")
	public List<OrderItem> checkout(@PathVariable Long userId){
		return checkoutService.checkout(userId);
	}
	
	@GetMapping("/{userId}")
	public List<OrderItem> getOrders(@PathVariable Long userId){
		
		return checkoutService.getOrders(userId);
	}
	
}
