package com.musinsa.coordination.model.response;

import com.musinsa.coordination.model.price.CategoriesBrandPrice;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LowestPriceBrandResponse {

    private final CategoriesBrandPrice lowestPrice;

    @Builder
    LowestPriceBrandResponse(CategoriesBrandPrice lowestPrice) {

        this.lowestPrice = lowestPrice;
    }
}
