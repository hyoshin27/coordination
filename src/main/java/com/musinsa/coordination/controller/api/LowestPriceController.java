package com.musinsa.coordination.controller.api;

import com.musinsa.coordination.model.response.LowestPriceBrandResponse;
import com.musinsa.coordination.model.response.LowestPriceCategoriesResponse;
import com.musinsa.coordination.model.response.MinMaxCategoryResponse;

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
    public ResponseEntity<LowestPriceCategoriesResponse> getLowestPriceByCategory() {

        List<CategoryBrandPrice> categoryBrandPrices = lowestPriceService.getLowByPrice();

        long totalPrice = categoryBrandPrices.stream()
                .mapToLong(CategoryBrandPrice::getPrice)
                .sum();
        LowestPriceCategoriesResponse dto = LowestPriceCategoriesResponse.builder()
                .categoryBrandPrices(categoryBrandPrices)
                .totalPrice(totalPrice)
                .build();

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/api/brand/lowest-price")
    public ResponseEntity<LowestPriceBrandResponse> getLowestPriceByBrand() {

        CategoriesBrandPrice categoriesBrandPrice = lowestPriceService.getLowByBrand();
        LowestPriceBrandResponse dto = LowestPriceBrandResponse.builder()
                .lowestPrice(categoriesBrandPrice)
                .build();

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/api/category/min-max-price")
    public ResponseEntity<MinMaxCategoryResponse> getMinMaxPriceByCategory(@RequestParam String categoryName) {

        Category category = Category.fromValue(categoryName);
        MinMaxCategoryResponse dto = lowestPriceService.getMinMaxPriceByCategory(category);

        return ResponseEntity.ok(dto);
    }
}
