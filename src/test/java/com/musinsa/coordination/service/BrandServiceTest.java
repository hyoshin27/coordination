package com.musinsa.coordination.service;

import com.musinsa.coordination.domain.Brand;
import com.musinsa.coordination.exception.BrandNotFoundException;
import com.musinsa.coordination.repository.BrandRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandService brandService;

    @DisplayName("브랜드를 찾지 못했을때 BrandNotFoundException 가 발생한다.")
    @Test
    void test1() {
        Mockito.when(brandRepository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(BrandNotFoundException.class, () -> brandService.findBrand(1));
    }

    @DisplayName("브랜드를 찾을수 있을때 정상적으로 조회 된다.")
    @Test
    void test2() {
        Brand brand = Brand.createBrand("N");

        Mockito.when(brandRepository.findById(any())).thenReturn(Optional.of(brand));

        Assertions.assertEquals("N", brand.getBrandName());
    }
}
