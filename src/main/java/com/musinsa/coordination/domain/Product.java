package com.musinsa.coordination.domain;

import com.musinsa.coordination.type.Category;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false, length = 50)
    private String productName;

    @ManyToOne
    @JoinColumn(name = "brandId")
    private Brand brand;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    private long price;

    @Builder
    public static Product createProduct(String productName,
                                        Brand brand,
                                        Category category,
                                        long price) {
        Product product = new Product();

        product.productName = productName;
        product.brand = brand;
        product.category = category;
        product.price = price;

        return product;
    }

    public void changeProduct(String productName,
                              Brand brand,
                              Category category,
                              long price) {
        this.productName = productName;
        this.brand = brand;
        this.category = category;
        this.price = price;
    }

    public String getBrandName() {
        if(Objects.isNull(this.brand)) {
            return "";
        }

        return brand.getBrandName();
    }
}
