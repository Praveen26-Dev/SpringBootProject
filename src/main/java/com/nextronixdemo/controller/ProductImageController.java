package com.nextronixdemo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nextronixdemo.dto.ProductImageResponseDto;
import com.nextronixdemo.service.ProductImageService;

@RestController
@RequestMapping("/api/products/{productId}/images")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;
    

    @PostMapping
    public String uploadImage(
    		    @PathVariable Long productId,
    		    @RequestParam(required = false) Long variantId,
            @RequestParam("files") List<MultipartFile> files
    )throws Exception {
    	      
        productImageService.uploadImages(productId,variantId,files);
        return "Image Uploaded Successfully";
       }
    
    @PutMapping("/{imageId}/set-primary")
    public String setPrimary(@PathVariable Long imageId) {
    	
    	productImageService.setPrimaryImage(imageId);
    	return "Primary Image is Set";
    }
    
    @GetMapping
    public List<ProductImageResponseDto> getImage(
    		@PathVariable Long productId,
		    @RequestParam(required = false) Long variantId
		    
		    ){
    	
    	return productImageService.getImages(productId,variantId);
    }
}
