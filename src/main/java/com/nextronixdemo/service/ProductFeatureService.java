package com.nextronixdemo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextronixdemo.dto.ProductFeatureBulkRequestDto;
import com.nextronixdemo.dto.ProductFeatureResponseDto;
import com.nextronixdemo.model.ProductFeature;
import com.nextronixdemo.repository.ProductFeatureRepository;

@Service
public class ProductFeatureService {

    @Autowired
    private ProductFeatureRepository repository;


    /* ================= BULK ADD FEATURES ================= */

    public void saveFeatures(
            Long productId,
            List <String> features
    ) {
       repository.deleteByProductId(productId);
       for(String f:features) {
    	   
    	    ProductFeature pf=new ProductFeature();
    	    
    	    pf.setProductId(productId);
    	    pf.setFeature(f);
    	    repository.save(pf);
       }
        
    }

    /* ================= GET FEATURES ================= */

    public List<String> getFeatures(Long productId){
    	return repository.findByProductId(productId)
                .stream()
                .map(ProductFeature::getFeature)
                .collect(java.util.stream.Collectors.toList());
    
    		
    }
}
