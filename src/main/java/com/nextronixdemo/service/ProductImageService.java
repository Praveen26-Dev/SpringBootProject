package com.nextronixdemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.nextronixdemo.config.CloudinaryConfig;
import com.nextronixdemo.dto.ProductImageResponseDto;
import com.nextronixdemo.model.ProductImage;
import com.nextronixdemo.repository.ProductImageRepository;

@Service
public class ProductImageService {

	@Autowired
	private ProductImageRepository productImageRepository;
	
	@Autowired
	private Cloudinary cloudinary;
	
	@Autowired
	private ModelMapper modelMapper;
   
	 /* ================= UPLOAD IMAGES ================= */

    public void uploadImages(
            Long productId,
            Long variantId,
            List<MultipartFile> files
    ) throws Exception {

        int order=1;
        for(MultipartFile file:files) {
        	  Map<?, ?> uploadResult = cloudinary.uploader().upload(
                   file.getBytes(),Map.of()        			  
        			  );
        	  ProductImage image = new ProductImage();
        	      image.setProductId(productId);
              image.setVariantId(variantId);
              image.setImageUrl(uploadResult.get("secure_url").toString());
        	      image.setDisplayOrder(order++);
        	      image.setIsPrimary(false);
        	      productImageRepository.save(image);
        }
        
        
    }
    
 // ================= SET PRIMARY IMAGE =================
    public void setPrimaryImage(Long imageId) {

        ProductImage image = productImageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        // Reset other images
        List<ProductImage> images =
                image.getVariantId() == null
                        ? productImageRepository.findByProductIdAndVariantIdIsNull(image.getProductId())
                        : productImageRepository.findByProductIdAndVariantId(
                                image.getProductId(),
                                image.getVariantId()
                        );

        images.forEach(img -> img.setIsPrimary(false));
        productImageRepository.saveAll(images);

        image.setIsPrimary(true);
        productImageRepository.save(image);
    }


    // ================= GET IMAGES FOR PRODUCT / VARIANT =================
    public List<ProductImageResponseDto> getImages(Long productId, Long variantId) {

        List<ProductImage> images =
                variantId == null
                        ? productImageRepository.findByProductIdAndVariantIdIsNull(productId)
                        : productImageRepository.findByProductIdAndVariantId(productId, variantId);

        return images.stream()
                .map(img -> modelMapper.map(img, ProductImageResponseDto.class))
                .toList();
    }


}
