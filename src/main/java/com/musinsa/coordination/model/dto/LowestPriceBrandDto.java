package com.musinsa.coordination.model.dto;

import com.musinsa.coordination.model.price.CategoriesBrandPrice;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LowestPriceBrandDto {

    private CategoriesBrandPrice lowestPrice;

    @Builder
    LowestPriceBrandDto(CategoriesBrandPrice lowestPrice){
        this.lowestPrice = lowestPrice;
    }
}
