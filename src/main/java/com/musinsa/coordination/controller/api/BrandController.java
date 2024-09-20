package com.musinsa.coordination.controller.api;

import com.musinsa.coordination.exception.BindingResultException;
import com.musinsa.coordination.exception.NameException;
import com.musinsa.coordination.model.dto.BrandDto;
import com.musinsa.coordination.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class BrandController {
    private final static int BRAND_NAME_MAX = 30;

    private final BrandService brandService;

    @GetMapping("/api/brands")
    public ResponseEntity<List<BrandDto>> getBrands() {
        List<BrandDto> brandList= brandService.findAllUseBrand().stream()
                .map(BrandDto::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(brandList);
    }

    @PostMapping("/api/brand")
    public ResponseEntity<Boolean> insertBrands(@RequestBody String brandName) {
        this.brandNameVaildation(brandName);

        brandService.insertBrand(brandName);

        return ResponseEntity.ok(Boolean.TRUE);
    }


    @PutMapping("/api/brand")
    public ResponseEntity<Boolean> updateBrand(@RequestBody @Valid BrandDto brandDto,
                                               BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new BindingResultException(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        this.brandNameVaildation(brandDto.getBrandName());

        brandService.updateBrand(brandDto.getBrandId(), brandDto.getBrandName());

        return ResponseEntity.ok(Boolean.TRUE);
    }

    @DeleteMapping("/api/brand")
    public ResponseEntity<Boolean> deleteBrand(@RequestBody long brandId) {
        brandService.deleteBrand(brandId);

        return ResponseEntity.ok(Boolean.TRUE);
    }

    private void brandNameVaildation(String brandName) {
        if(brandName.isEmpty()){
            throw new NameException("브랜드 이름을 입력해주세요.");
        }

        if(brandName.length() > BRAND_NAME_MAX) {
            throw new NameException("브랜드 이름은" + BRAND_NAME_MAX + "자까지 가능합니다.");
        }
    }
}
