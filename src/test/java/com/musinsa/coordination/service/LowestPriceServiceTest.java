package com.musinsa.coordination.service;

import com.musinsa.coordination.domain.Brand;
import com.musinsa.coordination.domain.Product;
import com.musinsa.coordination.exception.ProductNotFoundException;
import com.musinsa.coordination.model.price.CategoriesBrandPrice;
import com.musinsa.coordination.model.price.CategoryBrandPrice;
import com.musinsa.coordination.model.response.MinMaxCategoryResponse;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class LowestPriceServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private LowestPriceService lowestPriceService;

    @DisplayName("최저 가격을 찾지 못했을 때 ProductNotFoundException 가 발생 한다.")
    @Test
    void test1() {
        List<Product> products = Collections.emptyList();
        Mockito.when(productRepository.findAllByBrand_UseYn(any())).thenReturn(products);
        Assertions.assertThrows(ProductNotFoundException.class, () -> lowestPriceService.getLowPriceByCategory());
    }

    @DisplayName("최저 가격이 있을때 정상 적으로 조회 된다.")
    @Test
    void test2() {
        Brand brand = Brand.createBrand("N");
        Product top1 = Product.createProduct("상의", brand, Category.TOP, 1500);
        Product top2 = Product.createProduct("상의", brand, Category.TOP, 1100);
        Product top3 = Product.createProduct("상의", brand, Category.TOP, 1200);
        Product top4 = Product.createProduct("상의", brand, Category.TOP, 1600);

        Product pants1 = Product.createProduct("바지", brand, Category.PANTS, 11500);
        Product pants2 = Product.createProduct("바지", brand, Category.PANTS, 12100);
        Product pants3 = Product.createProduct("바지", brand, Category.PANTS, 11100);
        Product pants4 = Product.createProduct("바지", brand, Category.PANTS, 11600);

        List<Product> products = listOf(top1, top2, top3, top4, pants1, pants2, pants3, pants4);

        Mockito.when(productRepository.findAllByBrand_UseYn(any())).thenReturn(products);

        List<CategoryBrandPrice> lowByPrice = lowestPriceService.getLowPriceByCategory();
        Assertions.assertEquals(2, lowByPrice.size());

        Optional<CategoryBrandPrice> c = lowByPrice
                .stream()
                .filter(product -> product.getCategoryName().equals("상의"))
                .findFirst();
        Assertions.assertTrue(c.isPresent());
        Assertions.assertEquals(1100L, c.get().getPrice());

        Optional<CategoryBrandPrice> p = lowByPrice
                .stream()
                .filter(product -> product.getCategoryName().equals("바지"))
                .findFirst();
        Assertions.assertTrue(p.isPresent());
        Assertions.assertEquals(11100L, p.get().getPrice());
    }

    @DisplayName("브랜드별 최저 가격을 찾지 못했을 때 ProductNotFoundException 가 발생 한다.")
    @Test
    void test3() {
        List<Product> products = Collections.emptyList();
        Mockito.when(productRepository.findAllByBrand_UseYn(any())).thenReturn(products);
        Assertions.assertThrows(ProductNotFoundException.class, () -> lowestPriceService.getLowPriceByBrand());
    }

    @DisplayName("브랜드별 최저 가격이 있을때 정상 적으로 조회 된다.")
    @Test
    void test4() {
        Brand brand1 = Brand.createBrand("L");
        Product product1 = Product.createProduct("상의", brand1, Category.TOP, 150000);
        Product product2 = Product.createProduct("바지", brand1, Category.PANTS, 1100);
        Product product3 = Product.createProduct("스니커즈", brand1, Category.SNEAKERS, 1200);

        Brand brand2 = Brand.createBrand("M");
        Product product4 = Product.createProduct("상의", brand2, Category.TOP, 11100);
        Product product5 = Product.createProduct("바지", brand2, Category.PANTS, 12100);
        Product product6 = Product.createProduct("스니커즈", brand2, Category.SNEAKERS, 11100);

        Brand brand3 = Brand.createBrand("N");
        Product product7 = Product.createProduct("상의", brand3, Category.TOP, 12100);
        Product product8 = Product.createProduct("바지", brand3, Category.PANTS, 12100);
        Product product9 = Product.createProduct("스니커즈", brand3, Category.SNEAKERS, 11100);

        List<Product> products = listOf(product1, product2, product3, product4, product5, product6, product7, product8, product9);

        Mockito.when(productRepository.findAllByBrand_UseYn(any())).thenReturn(products);

        CategoriesBrandPrice lowByPrice = lowestPriceService.getLowPriceByBrand();
        Assertions.assertEquals("M", lowByPrice.getBrandName());
    }

    @DisplayName("카테고리 내에서 최저 가격 상품과 최고 가격 상품을 찾을 수 있다.")
    @Test
    void test5() {
        Brand brand1 = Brand.createBrand("L");
        Brand brand2 = Brand.createBrand("M");
        Brand brand3 = Brand.createBrand("N");

        Product product1 = Product.createProduct("가방1", brand1, Category.BAG, 150000);
        Product product2 = Product.createProduct("가방2", brand2, Category.BAG, 1100);
        Product product3 = Product.createProduct("가방3", brand3, Category.BAG, 1200);

        List<Product> products = listOf(product1, product2, product3);

        Mockito.when(productRepository.findByCategoryEqualsAndBrand_UseYnOrderByProductIdAsc(any(), any())).thenReturn(products);

        MinMaxCategoryResponse minMaxCategoryResponse = lowestPriceService.getMinMaxPriceByCategory(Category.BAG);

        Assertions.assertEquals("M", minMaxCategoryResponse.getMinPrice().getBrandName());
        Assertions.assertEquals("L", minMaxCategoryResponse.getMaxPrice().getBrandName());
    }

    @DisplayName("카테고리 내에서 상품이 없을때 최저 가격 상품, 최고 가격 상품 조회시 ProductNotFoundException 이 발생 한다")
    @Test
    void test6() {
        List<Product> products = Collections.emptyList();
        Mockito.when(productRepository.findByCategoryEqualsAndBrand_UseYnOrderByProductIdAsc(any(), any())).thenReturn(products);
        Assertions.assertThrows(ProductNotFoundException.class, () -> lowestPriceService.getMinMaxPriceByCategory(Category.BAG));
    }
}
