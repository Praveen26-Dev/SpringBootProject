package com.nextronixdemo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextronixdemo.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUserId(Long userId);

    Optional<CartItem> findByUserIdAndProductIdAndVariantId(
        Long userId,
        Long productId,
        Long variantId
    );

    void deleteByUserId(Long userId);
}
