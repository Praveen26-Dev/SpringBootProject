package com.nextronixdemo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextronixdemo.dto.CategoryRequest;
import com.nextronixdemo.model.Category;
import com.nextronixdemo.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void createCategoriesBulk(List<CategoryRequest> dtos) {

        Map<String, Category> tempMap = new HashMap<>();

        // 1️⃣ Save ROOT categories
        for (CategoryRequest dto : dtos) {
            if (dto.getParentTempId() == null) {
                Category category = modelMapper.map(dto, Category.class);
                category.setLevel(0); // root level
                categoryRepository.save(category);

                tempMap.put(dto.getTempId(), category);
            }
        }
        
        
        boolean pending;
        do {
            pending = false;

            for (CategoryRequest dto : dtos) {

                if (dto.getParentTempId() != null 
                        && !tempMap.containsKey(dto.getTempId())) {

                    Category parent = tempMap.get(dto.getParentTempId());

                    if (parent != null) {

                        Category category = modelMapper.map(dto, Category.class);
                 
                        category.setParentId(parent.getId());
                        category.setLevel(parent.getLevel() + 1);

                        categoryRepository.save(category);
                        tempMap.put(dto.getTempId(), category);

                    } else {
                        pending = true; // Parent not yet saved, try next loop
                    }
                }
            }

        } while (pending);


    }    
}
