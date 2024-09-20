package com.musinsa.coordination.model.price;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryBrandPrice {

    private String categoryName;

    private String brandName;

    private long price;

    public static CategoryBrandPrice from() {

        CategoryBrandPrice categoryBrandPrice = new CategoryBrandPrice();

        categoryBrandPrice.categoryName = "상의";
        categoryBrandPrice.brandName = "C";
        categoryBrandPrice.price = 10000;

        return categoryBrandPrice;
    }
}
