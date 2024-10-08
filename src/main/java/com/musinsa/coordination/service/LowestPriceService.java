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
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.minBy;

@Service
@RequiredArgsConstructor
public class LowestPriceService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<CategoryBrandPrice> getLowPriceByCategory() {
        List<Product> products = productRepository.findAllByBrand_UseYn(UseYn.Y);

        if (products.isEmpty()) {
            throw new ProductNotFoundException("최저가격을 찾을수 없습니다.");
        }

        Map<Category, List<Product>> productByCategory = products.stream()
                .collect(groupingBy(Product::getCategory));

        List<Product> minProducts = productByCategory.values()
                .stream()
                .map(this::findMinProduct)
                .toList();

        List<CategoryBrandPrice> categoryBrandPrices = new ArrayList<>();

        minProducts.forEach(product -> categoryBrandPrices.add(CategoryBrandPrice.from(product)));

        return categoryBrandPrices;
    }

    private Product findMinProduct(List<Product> productList) {
        return productList
                .stream()
                .min(Comparator.comparing(Product::getPrice))
                .orElseThrow(() -> new ProductNotFoundException("최저가격을 찾을수 없습니다."));
    }

    @Transactional(readOnly = true)
    public CategoriesBrandPrice getLowPriceByBrand() {
        List<Product> products = productRepository.findAllByBrand_UseYn(UseYn.Y);

        if (products.isEmpty()) {
            throw new ProductNotFoundException("최저가격을 찾을수 없습니다.");
        }

        Map<Brand, List<Product>> productByBrand = products
                .stream()
                .collect(groupingBy(Product::getBrand));

        //상품이 추가 되었을 때 카테고리별 상품 1개만 찾는 로직 추가
        Map<Brand, List<Product>> minProductByBrand = new HashMap<>();

        for (Map.Entry<Brand, List<Product>> entry : productByBrand.entrySet()) {
            Brand brand = entry.getKey();
            List<Product> productList = entry.getValue();
            Map<Category, Optional<Product>> minProductMap = productList.stream()
                    .collect(groupingBy(Product::getCategory, minBy(Comparator.comparing(Product::getPrice))));

            List<Product> minProducts = minProductMap.values()
                    .stream()
                    .map(Optional::get)
                    .toList();

            minProductByBrand.put(brand, minProducts);
        }

        Map.Entry<Brand, List<Product>> brandListEntry = minProductByBrand.entrySet()
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

    @Transactional(readOnly = true)
    public MinMaxCategoryResponse getMinMaxPriceByCategory(Category category) {
        List<Product> products = productRepository.findByCategoryEqualsAndBrand_UseYnOrderByProductIdAsc(
                category, UseYn.Y);

        if (products.isEmpty()) {
            throw new ProductNotFoundException("최저가격을 찾을수 없습니다.");
        }

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
