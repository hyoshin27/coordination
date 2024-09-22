package com.musinsa.coordination.controller.api;

import com.musinsa.coordination.domain.Brand;
import com.musinsa.coordination.model.response.ProductResponse;
import com.musinsa.coordination.model.request.ProductDeleteRequest;
import com.musinsa.coordination.model.request.ProductCreateRequest;
import com.musinsa.coordination.model.request.ProductUpdateRequest;
import com.musinsa.coordination.model.response.SuccessResponse;
import com.musinsa.coordination.service.BrandService;
import com.musinsa.coordination.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;
    private final BrandService brandService;

    @GetMapping("/api/products")
    public ResponseEntity<List<ProductResponse>> getProducts() {

        List<ProductResponse> productList = productService.findAllUseProduct()
                .stream()
                .map(ProductResponse::toDto)
                .toList();

        return ResponseEntity.ok(productList);
    }

    @PostMapping("/api/product")
    public ResponseEntity<SuccessResponse> insertProduct(@RequestBody @Valid ProductCreateRequest productCreateRequest) {

        Brand brand = brandService.findBrand(productCreateRequest.getBrandId());
        productService.insertProduct(
                brand,
                productCreateRequest.getProductName(),
                productCreateRequest.getCategory(),
                productCreateRequest.getPrice()
        );

        return ResponseEntity.ok(SuccessResponse.from("ok"));
    }

    @PutMapping("/api/product")
    public ResponseEntity<SuccessResponse> updateProduct(@RequestBody @Valid ProductUpdateRequest productUpdateRequest) {

        Brand brand = brandService.findBrand(productUpdateRequest.getBrandId());
        productService.updateProduct(
                brand,
                productUpdateRequest.getProductId(),
                productUpdateRequest.getProductName(),
                productUpdateRequest.getCategory(),
                productUpdateRequest.getPrice()
        );

        return ResponseEntity.ok(SuccessResponse.from("ok"));
    }

    @DeleteMapping("/api/product")
    public ResponseEntity<SuccessResponse> deleteProduct(@RequestBody ProductDeleteRequest productDeleteRequest) {

        productService.deleteProduct(productDeleteRequest.getProductId());
        return ResponseEntity.ok(SuccessResponse.from("ok"));
    }
}
