package com.musinsa.coordination.model.dto;

import com.musinsa.coordination.model.price.BrandPrice;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MinMaxCategoryDto {

    private String categoryName;

    private BrandPrice minPrice;

    private BrandPrice maxPrice;

    @Builder
    MinMaxCategoryDto(String categoryName,
                      BrandPrice minPrice,
                      BrandPrice maxPrice){
        this.categoryName = categoryName;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
}
