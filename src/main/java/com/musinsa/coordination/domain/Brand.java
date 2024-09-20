package com.musinsa.coordination.domain;

import com.musinsa.coordination.type.UseYn;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandId;

    @Column(nullable = false, length = 30)
    private String brandName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UseYn useYn;

    public static Brand createBrand(String brandName) {
        Brand brand = new Brand();

        brand.brandName = brandName;
        brand.useYn= UseYn.Y;

        return brand;
    }

    public void changeBrand(String brandName) {
        this.brandName = brandName;
    }

    public void deleteBrand() {
        this.useYn = UseYn.N;
    }
}
