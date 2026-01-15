package com.nextronixdemo.service;

import java.util.List;
import java.util.Objects;

import org.hibernate.internal.util.beans.BeanInfoHelper.ReturningBeanInfoDelegate;
import org.springframework.stereotype.Service;

import com.nextronixdemo.dto.CartItemResponseDto;
import com.nextronixdemo.model.CartItem;
import com.nextronixdemo.model.ProductImage;
import com.nextronixdemo.model.Variant;
import com.nextronixdemo.repository.CartItemRepository;
import com.nextronixdemo.repository.ProductImageRepository;
import com.nextronixdemo.repository.ProductRepository;
import com.nextronixdemo.repository.VariantRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService {

private final CartItemRepository cartRepo;
private final ProductRepository productRepository;
private final ProductImageRepository productImageRepository;
private final VariantRepository variantRepository;
private final VariantPricingService variantPricingService;

		public CartItem addToCart(Long userId, Long productId, Long variantId, int qty) {
		
		    Variant variant = variantRepository.findById(variantId)
		            .orElseThrow(() -> new RuntimeException("Variant not found"));
		
		    if (!variant.getProductId().equals(productId)) {
		        throw new RuntimeException("Variant does not belong to this product");
		    }
		
		    CartItem existing =
		            cartRepo.findByUserIdAndProductIdAndVariantId(userId, productId, variantId)
		                    .orElse(null);
		
		    if (existing != null) {
		        existing.setQuantity(existing.getQuantity() + qty);
		        return cartRepo.save(existing);
		    }
		
		    CartItem item = new CartItem();
		    item.setUserId(userId);
		    item.setProductId(productId);
		    item.setVariantId(variantId);
		    item.setQuantity(qty);
		
		    return cartRepo.save(item);
		}

    public void decreaseQty(Long id) {
    	CartItem item = cartRepo.findById(id).orElseThrow();
    	if(item.getQuantity()>1) {
    		item.setQuantity(item.getQuantity()-1);
    		cartRepo.save(item);
    	}
    	else {
    		cartRepo.delete(item);
    	}
    }
    
    public List<CartItemResponseDto> getCart(Long userId){
    	List<CartItem> items = cartRepo.findByUserId(userId);
    	
    	
    	return items.stream()
    			    .map(item -> {
    			    	var productOpt = productRepository.findById(item.getProductId());
    			    	if(productOpt.isEmpty()) {
    			    		cartRepo.delete(item);
    			    		return null;
    			    	}
    			    	var product = productOpt.get();
    			    	List<ProductImage> imgs = productImageRepository.findByProductIdAndVariantId(item.getProductId(), item.getVariantId());
    			    	
    			    	if(imgs.isEmpty()) {
    			    		imgs = productImageRepository.findByProductIdAndVariantIdIsNull(item.getProductId());
    			    	}
    			    	
    			    	String img = imgs.isEmpty()? null : imgs.get(0).getImageUrl();
    			    	
    			    	Variant variant = null;
    			    	if(item.getVariantId()!=null) {
    			    	variant = variantRepository.findById(item.getVariantId()).orElse(null);
    			    	}
    			    	else {
    			    		cartRepo.delete(item);
    			    		return null;
    			    	}
    			    	
    			    	double price = 0.0;
    			    	
    			    	if(variant !=null) {
    			    		var pricing = variantPricingService.getPricing(variant.getId());
    			    		price = pricing.getFinalPrice();
    			    	}
    			    	return CartItemResponseDto.from(item,product.getName(),img,price);
    			    	
    			    })
    			    .filter(Objects::nonNull)
    			    .toList();
    }
    
    public void remove(Long id) {
        cartRepo.deleteById(id);
    }

    // ================= CLEAR =================

    @Transactional
    public void clear(Long userId) {
        cartRepo.deleteByUserId(userId);
    }

    // ================= COUNT =================

    public int countItems(Long userId) {
        return cartRepo.findByUserId(userId)
                .stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }
}
