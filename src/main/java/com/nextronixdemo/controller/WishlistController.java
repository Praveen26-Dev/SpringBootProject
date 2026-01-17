package com.nextronixdemo.controller;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nextronixdemo.dto.WishlistItemResponseDto;
import com.nextronixdemo.model.WishlistItem;
import com.nextronixdemo.service.WishlistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishlistController {

	private final WishlistService wishlistService;
	
	@PostMapping("/add")
	public WishlistItem addToWishlist(@RequestBody WishlistItem item) {
		
		return wishlistService.add(item);
	}
	
	@GetMapping("/{userId}")
	public List<WishlistItemResponseDto> getWishlist(@PathVariable  Long userId){
		
		return wishlistService.get(userId);
	}
	
	@DeleteMapping("/remove")
	public void remove(@RequestParam Long userId,@RequestParam Long productId) {
		wishlistService.remove(userId,productId);
	}
}
