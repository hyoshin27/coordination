package com.musinsa.coordination.service;

import com.musinsa.coordination.domain.Brand;
import com.musinsa.coordination.domain.Product;
import com.musinsa.coordination.exception.ProductNotFoundException;
import com.musinsa.coordination.repository.ProductRepository;
import com.musinsa.coordination.type.Category;
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
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @DisplayName("상품을 찾지 못했을 때 ProductNotFoundException 가 발생 한다.")
    @Test
    void test1() {
        Mockito.when(productRepository.findById(any())).thenReturn(Optional.empty());

        Brand brand = Brand.createBrand("N");

        Assertions.assertThrows(
                ProductNotFoundException.class,
                () -> productService.updateProduct(brand, 1L, "상의", Category.TOP, 1000)
        );
    }

    @DisplayName("상품을 찾을수 있을때 정상 적으로 조회 된다.")
    @Test
    void test2() {
        Brand brand = Brand.createBrand("N");
        Product product = Product.createProduct("상의", brand, Category.TOP, 1000);

        Mockito.when(productRepository.findById(any())).thenReturn(Optional.of(product));

        Assertions.assertDoesNotThrow(() -> productService.updateProduct(brand, 1L, "상의", Category.TOP, 1000));
    }
}
