package com.musinsa.coordination.model.response;

import com.musinsa.coordination.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponse {

    private Long productId;
    private String productName;
    private String brandName;
    private String category;
    private long price;

    public static ProductResponse toDto(Product product) {

        ProductResponse dto = new ProductResponse();
        dto.productId = product.getProductId();
        dto.productName = product.getProductName();
        dto.brandName = product.getBrand().getBrandName();
        dto.category = product.getCategory().getDesc();
        dto.price = product.getPrice();

        return dto;
    }
}
