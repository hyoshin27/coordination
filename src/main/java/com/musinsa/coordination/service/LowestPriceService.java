package com.musinsa.coordination.service;

import com.musinsa.coordination.domain.Brand;
import com.musinsa.coordination.domain.Product;
import com.musinsa.coordination.exception.ProductNotFoundException;
import com.musinsa.coordination.model.response.MinMaxCategoryResponse;
import com.musinsa.coordination.model.price.BrandPrice;
import com.musinsa.coordination.model.price.CategoriesBrandPrice;
import com.musinsa.coordination.model.price.CategoryBrandPrice;
import com.musinsa.coordination.model.price.CategoryPrice;
import com.musinsa.coordination.repository.ProductRepository;
import com.musinsa.coordination.type.Category;
import com.musinsa.coordination.type.UseYn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LowestPriceService {

    private final ProductRepository productRepository;

    public List<CategoryBrandPrice> getLowByPrice() {
        List<Product> products = productRepository.findAllByBrand_UseYn(UseYn.Y);

        Map<Category, List<Product>> productByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        List<Product> minProducts = productByCategory.values()
                .stream()
                .map(this::findMinProduct)
                .toList();

        List<CategoryBrandPrice> categoryBrandPrices = new ArrayList<>();

        minProducts.forEach(product -> categoryBrandPrices.add(CategoryBrandPrice.from(product)));

        return categoryBrandPrices;
    }

    private Product findMinProduct(List<Product> productList) {
        return productList.stream().min(
                (product1, product2) -> (int) (product1.getPrice() - product2.getPrice())
        ).orElseThrow(() -> new ProductNotFoundException("최저가격을 찾을수 없습니다."));
    }

    public CategoriesBrandPrice getLowByBrand() {
        List<Product> products = productRepository.findAllByBrand_UseYn(UseYn.Y);

        Map<Brand, List<Product>> productByBrand = products.stream()
                .collect(Collectors.groupingBy(Product::getBrand));

        Map.Entry<Brand, List<Product>> brandListEntry = productByBrand.entrySet()
                .stream()
                .min(this.priceComparator())
                .orElseThrow(() -> new ProductNotFoundException("브랜드 상품을 찾을수 없습니다."));

        List<CategoryPrice> categoryPriceList = new ArrayList<>();
        brandListEntry.getValue()
                .forEach((product -> categoryPriceList.add(CategoryPrice.from(product))));

        return CategoriesBrandPrice.builder()
                .brandName(brandListEntry.getKey().getBrandName())
                .categories(categoryPriceList)
                .totalPrice(brandListEntry.getValue().stream().mapToLong(Product::getPrice).sum())
                .build();
    }

    private Comparator<Map.Entry<Brand, List<Product>>> priceComparator() {
        return (product1, product2) -> (int) (product1.getValue().stream().mapToLong(Product::getPrice).sum()
                - product2.getValue().stream().mapToLong(Product::getPrice).sum());
    }

    public MinMaxCategoryResponse getMinMaxPriceByCategory(Category category) {
        List<Product> products = productRepository.findByCategoryEqualsAndBrand_UseYnOrderByProductIdAsc(
                category, UseYn.Y);

        Product minProduct = products.stream()
                .min(Comparator.comparing(Product::getPrice))
                .orElseThrow(() -> new ProductNotFoundException("최저 가격을 찾을수 없습니다."));

        Product maxProduct = products.stream()
                .max(Comparator.comparing(Product::getPrice))
                .orElseThrow(() -> new ProductNotFoundException("최대 가격을 찾을수 없습니다."));

        return MinMaxCategoryResponse.builder()
                .categoryName(category.getDesc())
                .minPrice(BrandPrice.from(minProduct.getBrandName(), minProduct.getPrice()))
                .maxPrice(BrandPrice.from(maxProduct.getBrandName(), maxProduct.getPrice()))
                .build();
    }
}
