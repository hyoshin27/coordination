package com.musinsa.coordination.model.price;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BrandPrice {

    private String brandName;
    private long price;

    public static BrandPrice from(String brandName,
                                  long price) {

        BrandPrice brandPrice = new BrandPrice();
        brandPrice.brandName = brandName;
        brandPrice.price = price;

        return brandPrice;
    }
}
