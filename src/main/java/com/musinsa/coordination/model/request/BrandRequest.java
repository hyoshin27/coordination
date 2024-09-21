package com.musinsa.coordination.model.request;

import com.musinsa.coordination.domain.Brand;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
public class BrandRequest {

    private Long brandId;

    @NotNull(message = "브랜드 이름을 입력해주세요.")
    @Length(min = 1, max = 30, message = "브랜드 길이는 1~30자까지 입니다.")
    private String brandName;

    public static BrandRequest toDto(Brand brand){
        BrandRequest brandRequest = new BrandRequest();

        brandRequest.brandId = brand.getBrandId();
        brandRequest.brandName = brand.getBrandName();

        return brandRequest;
    }
}
