package com.musinsa.coordination.model.dto;

import com.musinsa.coordination.domain.Brand;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
public class BrandDto {

    private Long brandId;

    @NotNull(message = "브랜드 이름을 입력해주세요.")
    @Length(min = 1, max = 30, message = "브랜드 길이는 1~30자까지 입니다.")
    private String brandName;

    public static BrandDto toDto(Brand brand){
        BrandDto brandDto = new BrandDto();

        brandDto.brandId = brand.getBrandId();
        brandDto.brandName = brand.getBrandName();

        return brandDto;
    }
}
