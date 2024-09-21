package com.musinsa.coordination.service;

import com.musinsa.coordination.domain.Brand;
import com.musinsa.coordination.domain.Product;
import com.musinsa.coordination.exception.ProductNotFoundException;
import com.musinsa.coordination.model.dto.MinMaxCategoryDto;
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

        if(products.isEmpty()) {
            throw new ProductNotFoundException("상품이 없습니다.");
        }

        Map<Category, List<Product>> productByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));


        List<Product> optionalStream = productByCategory.values()
                .stream()
                .map(productList -> productList.stream().min((product1, product2) -> (int) (product1.getPrice() - product2.getPrice()) ).get())
                .collect(Collectors.toList());

        List<CategoryBrandPrice> categoryBrandPrices = new ArrayList<>();

        optionalStream.forEach(product -> {
            categoryBrandPrices.add(CategoryBrandPrice.from(product));
        });

        return categoryBrandPrices;
    }

    public CategoriesBrandPrice getLowByBrand() {
        List<Product> products = productRepository.findAllByBrand_UseYn(UseYn.Y);

        if(products.isEmpty()) {
            throw new ProductNotFoundException("상품이 없습니다.");
        }

        Map<Brand, List<Product>> productByBrand = products.stream()
                .collect(Collectors.groupingBy(Product::getBrand));

        Map.Entry<Brand, List<Product>> brandListEntry = productByBrand.entrySet()
                .stream()
                .min((product1, product2) -> (int) (product1.getValue().stream().mapToLong(Product::getPrice).sum()
                        - product2.getValue().stream().mapToLong(Product::getPrice).sum())).get();

        List<CategoryPrice> categoryPriceList = new ArrayList<>();

        brandListEntry.getValue().forEach((product -> {
            categoryPriceList.add(CategoryPrice.from(product));
        }));

        CategoriesBrandPrice categoriesBrandPrice = CategoriesBrandPrice.builder()
                .brandName(brandListEntry.getKey().getBrandName())
                .categories(categoryPriceList)
                .totalPrice(brandListEntry.getValue().stream().mapToLong(Product::getPrice).sum())
                .build();

        return categoriesBrandPrice;
    }

    public MinMaxCategoryDto getMinMaxPriceByCategory(Category category) {
        List<Product> products = productRepository.findByCategoryEqualsAndBrand_UseYnOrderByProductIdAsc(
                category, UseYn.Y);

        if(products.isEmpty()) {
            throw new ProductNotFoundException("상품이 없습니다.");
        }

        Product minProduct = products.stream()
                .min(Comparator.comparing(Product::getPrice))
                .get();

        Product maxProduct = products.stream()
                .max(Comparator.comparing(Product::getPrice))
                .get();

        MinMaxCategoryDto dto = MinMaxCategoryDto.builder()
                .categoryName(category.getDesc())
                .minPrice(BrandPrice.from(minProduct.getBrandName(), minProduct.getPrice()))
                .maxPrice(BrandPrice.from(maxProduct.getBrandName(), maxProduct.getPrice()))
                .build();

        return dto;
    }
}
