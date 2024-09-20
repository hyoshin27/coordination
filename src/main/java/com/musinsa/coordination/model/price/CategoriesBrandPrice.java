package com.musinsa.coordination.model.price;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CategoriesBrandPrice {

    private String brandName;

    private List<CategoryPrice> categories;

    private long totalPrice;

    @Builder
    CategoriesBrandPrice(String brandName,
                         List<CategoryPrice> categories,
                         long totalPrice) {
        this.brandName = brandName;
        this.categories = categories;
        this.totalPrice = totalPrice;
    }
}
