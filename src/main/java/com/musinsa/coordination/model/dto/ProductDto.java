package com.musinsa.coordination.model.dto;

import com.musinsa.coordination.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    private Long productId;

    private String productName;

    private String brandName;

    private String category;

    private long price;

    public static ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();

        dto.productId = product.getProductId();
        dto.productName = product.getProductName();
        dto.brandName = product.getBrand().getBrandName();
        dto.category = product.getCategory().getDesc();
        dto.price = product.getPrice();

        return dto;
    }
}
