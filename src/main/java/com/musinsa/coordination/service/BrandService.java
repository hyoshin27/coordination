package com.musinsa.coordination.service;

import com.musinsa.coordination.domain.Brand;
import com.musinsa.coordination.exception.BrandNotFoundException;
import com.musinsa.coordination.repository.BrandRepository;
import com.musinsa.coordination.type.UseYn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    @Transactional(readOnly = true)
    public List<Brand> findAllUseBrand() {

        return brandRepository.findAllByUseYnEquals(UseYn.Y);
    }

    @Transactional
    public void insertBrand(String brandName) {

        Brand brand = Brand.createBrand(brandName);

        brandRepository.save(brand);
    }

    @Transactional
    public void updateBrand(long brandId, String brandName) {

        Brand brand = this.findBrand(brandId);
        brand.changeBrand(brandName);

        brandRepository.save(brand);
    }

    @Transactional
    public void deleteBrand(long brandId) {

        Brand brand = this.findBrand(brandId);
        brand.deleteBrand();

        brandRepository.save(brand);
    }

    @Transactional(readOnly = true)
    public Brand findBrand(long brandId) {

        return brandRepository.findByBrandIdEqualsAndUseYnEquals(brandId, UseYn.Y)
                .orElseThrow(() -> new BrandNotFoundException("브랜드 정보가 잘못 되었습니다."));
    }
}
