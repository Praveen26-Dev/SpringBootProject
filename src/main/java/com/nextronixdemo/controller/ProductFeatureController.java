package com.nextronixdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nextronixdemo.model.ProductFeature;
import com.nextronixdemo.service.ProductFeatureService;

@RestController
@RequestMapping("/api/feature/{productId}")
public class ProductFeatureController {

    @Autowired
    private ProductFeatureService productFeatureService;

    /* ================= SAVE FEATURES (BULK) ================= */

    @PostMapping("/create")
    public String saveFeatures(
            @PathVariable Long productId,
            @RequestBody List<String> features
    ) {

    	System.out.println("FEATURE BULK API HIT");

        productFeatureService.saveFeatures(productId, features);
        return "Product features saved successfully";
    }

    /* ================= GET FEATURES ================= */

    @GetMapping
    public List<ProductFeature> getFeatures(
            @PathVariable Long productId
    ) {
        return productFeatureService.getFeatures(productId);
    }
}
