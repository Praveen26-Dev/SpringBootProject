package com.nextronixdemo.dto;

import java.util.List;
import lombok.Data;

@Data
public class ProductFeatureBulkRequestDto {
    private List<String> features;
}

