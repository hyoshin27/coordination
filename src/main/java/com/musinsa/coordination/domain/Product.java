package com.musinsa.coordination.domain;

import com.musinsa.coordination.type.Category;
import com.musinsa.coordination.type.UseYn;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false, length = 50)
    private String productName;

    @Column(nullable = false)
    private Long brandId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;


    private long price;
}
