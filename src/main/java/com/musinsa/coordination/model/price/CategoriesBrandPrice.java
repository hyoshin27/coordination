package com.musinsa.coordination.model.price;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CategoriesBrandPrice {

    private final String brandName;
    private final List<CategoryPrice> categories;
    private final long totalPrice;

    @Builder
    CategoriesBrandPrice(String brandName,
                         List<CategoryPrice> categories,
                         long totalPrice) {

        this.brandName = brandName;
        this.categories = categories;
        this.totalPrice = totalPrice;
    }
}
