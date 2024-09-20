package com.musinsa.coordination.model.dto;

import com.musinsa.coordination.type.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


@Getter
@Setter
@NoArgsConstructor
public class ProductInsertDto {

    private Long productId;

    @NotNull(message = "상품 이름을 입력해주세요.")
    @Length(min = 1, max = 50, message = "상품 길이는 1~50자까지 입니다.")
    private String productName;

    @NotNull(message = "브랜드를 입력해주세요")
    private Long brandId;

    @NotNull(message = "카테고리를 입력해주세요.")
    @Enumerated(EnumType.STRING)
    private Category category;

    @NotNull(message = "가격을 입력해주세요.")
    private Long price;
}
