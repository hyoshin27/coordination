package com.musinsa.coordination.model.price;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryPrice {

    private String categoryName;

    private long price;

    public static CategoryPrice from () {
        CategoryPrice categoryPrice = new CategoryPrice();

        categoryPrice.categoryName  = "상의";
        categoryPrice.price  = 10100;

        return categoryPrice;
    }
}
