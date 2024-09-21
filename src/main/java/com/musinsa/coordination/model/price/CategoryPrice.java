package com.musinsa.coordination.model.price;

import com.musinsa.coordination.domain.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryPrice {

    private String categoryName;

    private long price;

    public static CategoryPrice from (Product product) {
        CategoryPrice categoryPrice = new CategoryPrice();

        categoryPrice.categoryName  = product.getCategory().getDesc();
        categoryPrice.price  = product.getPrice();

        return categoryPrice;
    }
}
