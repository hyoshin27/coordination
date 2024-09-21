package com.musinsa.coordination.controller.api;

import com.musinsa.coordination.model.dto.LowestPriceBrandDto;
import com.musinsa.coordination.model.dto.LowestPriceCategoriesDto;
import com.musinsa.coordination.model.dto.MinMaxCategoryDto;

import com.musinsa.coordination.model.price.CategoriesBrandPrice;
import com.musinsa.coordination.model.price.CategoryBrandPrice;
import com.musinsa.coordination.service.LowestPriceService;
import com.musinsa.coordination.type.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class LowestPriceController {

    private final LowestPriceService lowestPriceService;

    @GetMapping("/api/category/lowest-price")
    public ResponseEntity<LowestPriceCategoriesDto> getLowestPriceByCategory() {
        List<CategoryBrandPrice> categoryBrandPrices = lowestPriceService.getLowByPrice();

        long totalPrice = categoryBrandPrices.stream()
                .mapToLong(CategoryBrandPrice::getPrice)
                .sum();

        LowestPriceCategoriesDto dto = LowestPriceCategoriesDto.builder()
                .categoryBrandPrices(categoryBrandPrices)
                .totalPrice(totalPrice)
                .build();

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/api/brand/lowest-price")
    public ResponseEntity<LowestPriceBrandDto> getLowestPriceByBrand() {
        CategoriesBrandPrice categoriesBrandPrice = lowestPriceService.getLowByBrand();

        LowestPriceBrandDto dto = LowestPriceBrandDto.builder()
                .lowestPrice(categoriesBrandPrice)
                .build();

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/api/category/min-max-price")
    public ResponseEntity<MinMaxCategoryDto> getMinMaxPriceByCategory(@RequestParam String categoryName) {
        Category category = Category.fromValue(categoryName);

        MinMaxCategoryDto dto = lowestPriceService.getMinMaxPriceByCategory(category);

        return ResponseEntity.ok(dto);
    }
}
