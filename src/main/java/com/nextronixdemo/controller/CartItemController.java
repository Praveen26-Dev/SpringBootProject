package com.nextronixdemo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextronixdemo.dto.CartItemRequestDto;
import com.nextronixdemo.dto.CartItemResponseDto;
import com.nextronixdemo.model.CartItem;
import com.nextronixdemo.service.CartItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartService;

    @PostMapping("/add")
    public CartItem add(@RequestBody CartItemRequestDto req) {
        return cartService.addToCart(
            req.getUserId(),
            req.getProductId(),
            req.getVariantId(),
            req.getQuantity()
        );
    }

    @GetMapping("/{userId}")
    public List<CartItemResponseDto> get(@PathVariable Long userId) {
        return cartService.getCart(userId);
    }

    @PostMapping("/decrease/{id}")
    public void decrease(@PathVariable Long id) {
        cartService.decreaseQty(id);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        cartService.remove(id);
    }

    @DeleteMapping("/clear/{userId}")
    public void clear(@PathVariable Long userId) {
        cartService.clear(userId);
    }

    @GetMapping("/count/{userId}")
    public int count(@PathVariable Long userId) {
        return cartService.countItems(userId);
    }
}
