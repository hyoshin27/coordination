package com.musinsa.coordination.controller.api;

import com.musinsa.coordination.model.dto.LowestPriceBrandDto;
import com.musinsa.coordination.model.dto.LowestPriceCategoriesDto;
import com.musinsa.coordination.model.dto.MinMaxCategoryDto;
import com.musinsa.coordination.model.price.BrandPrice;
import com.musinsa.coordination.model.price.CategoriesBrandPrice;
import com.musinsa.coordination.model.price.CategoryPrice;
import com.musinsa.coordination.model.price.CategoryBrandPrice;
import com.musinsa.coordination.type.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RequiredArgsConstructor
@RestController
public class LowestPriceController {

    @GetMapping("/api/category/lowest-price")
    public ResponseEntity<LowestPriceCategoriesDto> getLowestPriceByCategory() {
        LowestPriceCategoriesDto dto = LowestPriceCategoriesDto.builder()
                .categoryBrandPrices(Arrays.asList(CategoryBrandPrice.from()))
                .totalPrice(10000)
                .build();

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/api/brand/lowest-price")
    public ResponseEntity<LowestPriceBrandDto> getLowestPriceByBrand() {
        CategoriesBrandPrice categoriesBrandPrice = CategoriesBrandPrice.builder()
                .brandName("D")
                .categories(Arrays.asList(CategoryPrice.from()))
                .totalPrice(10100)
                .build();

        LowestPriceBrandDto dto = LowestPriceBrandDto.builder()
                .lowestPrice(categoriesBrandPrice)
                .build();

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/api/category/min-max-price")
    public ResponseEntity<MinMaxCategoryDto> getMinMaxPriceByCategory(@RequestParam String categoryName) {

        Category category = Category.fromValue(categoryName);
        BrandPrice minPrice = BrandPrice.from("C",10000);
        BrandPrice maxPrice = BrandPrice.from("I",11400);

        MinMaxCategoryDto dto = MinMaxCategoryDto.builder()
                .categoryName(category.getDesc())
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .build();

        return ResponseEntity.ok(dto);
    }
}
