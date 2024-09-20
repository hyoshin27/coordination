package com.musinsa.coordination.service;

import com.musinsa.coordination.domain.Brand;
import com.musinsa.coordination.exception.BrandNotFoundException;
import com.musinsa.coordination.repository.BrandRepository;
import com.musinsa.coordination.type.UseYn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public List<Brand> findAllUseBrand() {

        return brandRepository.findAllByUseYnEquals(UseYn.Y);
    }

    public void insertBrand(String brandName) {
        Brand brand = Brand.createBrand(brandName);

        brandRepository.save(brand);
    }

    public void updateBrand(long brandId, String brandName) {
        Brand brand = this.findBrand(brandId);

        brand.changeBrand(brandName);

        brandRepository.save(brand);
    }

    public void deleteBrand(long brandId) {
        Brand brand = this.findBrand(brandId);

        brand.deleteBrand();

        brandRepository.save(brand);
    }

    public Brand findBrand(long brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException("브랜드 정보가 잘못 되었습니다."));

        return brand;
    }
}
