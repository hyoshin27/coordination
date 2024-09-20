package com.musinsa.coordination.model.dto;

import com.musinsa.coordination.model.price.CategoryBrandPrice;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class LowestPriceCategoriesDto {

    private List<CategoryBrandPrice> categoryBrandPrices;

    private long totalPrice;

    @Builder
    LowestPriceCategoriesDto(List<CategoryBrandPrice> categoryBrandPrices,
                            long totalPrice) {
        this.categoryBrandPrices = categoryBrandPrices;
        this.totalPrice = totalPrice;
    }
}
