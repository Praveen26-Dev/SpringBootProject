package com.nextronixdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextronixdemo.dto.VariantDiscountRequestDto;
import com.nextronixdemo.dto.VariantPriceRequestDto;
import com.nextronixdemo.dto.VariantPricingResponseDto;
import com.nextronixdemo.model.DiscountType;
import com.nextronixdemo.model.VariantDiscount;
import com.nextronixdemo.model.VariantPrice;
import com.nextronixdemo.repository.VariantDiscountRepository;
import com.nextronixdemo.repository.VariantPriceRepository;

@Service
public class VariantPricingService {

    @Autowired
    private VariantPriceRepository priceRepository;

    @Autowired
    private VariantDiscountRepository discountRepository;

    /* ================= SET PRICE ================= */

    public void setPrice(Long variantId, VariantPriceRequestDto request) {

        VariantPrice price = priceRepository
                .findByVariantId(variantId)
                .orElse(new VariantPrice());

        price.setVariantId(variantId);
        price.setMrp(request.getMrp());
        price.setSellingPrice(request.getSellingPrice());

        priceRepository.save(price);
    }

    /* ================= SET DISCOUNT ================= */

    public void setDiscount(Long variantId, VariantDiscountRequestDto request) {

        VariantDiscount discount = discountRepository.findByVariantIdAndIsActiveTrue(variantId).orElse(new VariantDiscount());
        
        discount.setVariantId(variantId);
        discount.setDiscountType(request.getDiscountType());
        discount.setDiscountValue(request.getDiscountValue());
        discount.setIsActive(true);

        discountRepository.save(discount);
    }

    /* ================= GET PRICING ================= */

    public VariantPricingResponseDto getPricing(Long variantId) {

        VariantPrice price = priceRepository
                .findByVariantId(variantId)
                .orElseThrow(() ->
                        new RuntimeException("Price not found for variant"));

        double discountAmount = 0.0;
        var  discountOpt = discountRepository
                .findByVariantIdAndIsActiveTrue(variantId);

     

        if (discountOpt.isPresent()) 
        {
        	 VariantDiscount discount = discountOpt.get();
            if (discount.getDiscountType() == DiscountType.PERCENT) {
                discountAmount =
                        price.getSellingPrice()
                        * discount.getDiscountValue() / 100;

            } else {
                discountAmount = discount.getDiscountValue();
            }
        }

        VariantPricingResponseDto response = new VariantPricingResponseDto();
        response.setMrp(price.getMrp());
        response.setSellingPrice(price.getSellingPrice());
        response.setDiscount(discountAmount);
        response.setFinalPrice(price.getSellingPrice() - discountAmount);

        return response;
    }
}
