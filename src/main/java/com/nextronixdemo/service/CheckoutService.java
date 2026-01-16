package com.nextronixdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.nextronixdemo.controller.CartItemController;
import com.nextronixdemo.dto.VariantPricingResponseDto;
import com.nextronixdemo.model.CartItem;
import com.nextronixdemo.model.OrderItem;
import com.nextronixdemo.model.Variant;
import com.nextronixdemo.repository.CartItemRepository;
import com.nextronixdemo.repository.OrderRepository;
import com.nextronixdemo.repository.VariantRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final CartItemController cartItemController;
	
	private final OrderRepository orderRepository;
	private final CartItemRepository cartItemRepository;
	private final VariantRepository variantRepository;
	private final VariantPricingService variantPricingService;	
		@Transactional
		
	    public List<OrderItem> checkout(Long userId) {

	        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
	        List<OrderItem> orderItems = new ArrayList<>();

	        for (CartItem c : cartItems) {

	            // Get variant to read price
	            Variant variant = variantRepository.findById(c.getVariantId())
	                    .orElse(null);

	            double finalPrice = 0.0;

	            if (variant != null) {
	                VariantPricingResponseDto pricing =
	                        variantPricingService.getPricing(variant.getId());
	                finalPrice = pricing.getFinalPrice();   // APPLY DISCOUNT
	            }

	            OrderItem o = new OrderItem();
	            o.setUserId(userId);
	            o.setProductId(c.getProductId());
	            o.setVariantId(c.getVariantId());
	            o.setQuantity(c.getQuantity());
	            o.setPrice(finalPrice);    // SAME PRICE AS CART
	            o.setOrderStatus("CONFIRMED");

	            orderItems.add(orderRepository.save(o));
	        }

	        cartItemRepository.deleteByUserId(userId);   // clear cart
	        return orderItems;
	    }
		
		
public List<OrderItem> getOrders(Long userId) {
	// TODO Auto-generated method stub
	return orderRepository.findByUserId(userId);
}

}
