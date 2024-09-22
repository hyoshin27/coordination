package com.musinsa.coordination.model.response;

import com.musinsa.coordination.model.price.BrandPrice;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MinMaxCategoryResponse {

    private final String categoryName;
    private final BrandPrice minPrice;
    private final BrandPrice maxPrice;

    @Builder
    MinMaxCategoryResponse(String categoryName,
                           BrandPrice minPrice,
                           BrandPrice maxPrice) {

        this.categoryName = categoryName;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
}
