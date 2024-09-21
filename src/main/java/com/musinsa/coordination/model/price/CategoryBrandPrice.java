package com.musinsa.coordination.model.price;

import com.musinsa.coordination.domain.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryBrandPrice {

    private String categoryName;

    private String brandName;

    private long price;

    public static CategoryBrandPrice from(Product product) {

        CategoryBrandPrice categoryBrandPrice = new CategoryBrandPrice();

        categoryBrandPrice.categoryName = product.getCategory().getDesc();
        categoryBrandPrice.brandName = product.getBrandName();
        categoryBrandPrice.price = product.getPrice();

        return categoryBrandPrice;
    }
}
