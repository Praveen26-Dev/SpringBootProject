package com.nextronixdemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nextronixdemo.dto.WishlistItemResponseDto;
import com.nextronixdemo.model.Product;
import com.nextronixdemo.model.ProductImage;
import com.nextronixdemo.model.WishlistItem;
import com.nextronixdemo.repository.ProductImageRepository;
import com.nextronixdemo.repository.ProductRepository;
import com.nextronixdemo.repository.WishlistRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepo;
    private final ProductRepository productRepo;
    private final ProductImageRepository imageRepo;

    /* ================= ADD TO WISHLIST ================= */

    public WishlistItem add(WishlistItem item) {

        boolean exists = wishlistRepo.existsByUserIdAndProductId(
                item.getUserId(),
                item.getProductId()
        );

        if (exists) {
            throw new RuntimeException("Already in wishlist");
        }

        return wishlistRepo.save(item);
    }


    /* ================= GET USER WISHLIST ================= */

    public List<WishlistItemResponseDto> get(Long userId) {
        return wishlistRepo.findByUserId(userId)
                .stream()
                .map(w -> {

                    Product p = productRepo.findById(w.getProductId())
                            .orElse(null);

                    ProductImage img =
                            imageRepo.findFirstByProductId(w.getProductId())
                                     .orElse(null);

                    String url = img != null ? img.getImageUrl() : null;

                    return new WishlistItemResponseDto(
                            w.getId(),
                            w.getProductId(),
                            p != null ? p.getName() : null,
                            url
                    );
                })
                .toList();
    }

    /* ================= REMOVE ================= */

    @Transactional
    public void remove(Long userId, Long productId) {
        wishlistRepo.deleteByUserIdAndProductId(userId, productId);
    }
}
