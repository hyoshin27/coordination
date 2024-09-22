package com.musinsa.coordination.model.response;

import com.musinsa.coordination.model.price.CategoryBrandPrice;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class LowestPriceCategoriesResponse {

    private final List<CategoryBrandPrice> categoryBrandPrices;
    private final long totalPrice;

    @Builder
    LowestPriceCategoriesResponse(List<CategoryBrandPrice> categoryBrandPrices,
                                  long totalPrice) {

        this.categoryBrandPrices = categoryBrandPrices;
        this.totalPrice = totalPrice;
    }
}
