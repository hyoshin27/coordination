package com.musinsa.coordination.controller.api;

import com.musinsa.coordination.domain.Brand;
import com.musinsa.coordination.exception.BindingResultException;
import com.musinsa.coordination.model.dto.ProductDto;
import com.musinsa.coordination.model.request.ProductInsertRequest;
import com.musinsa.coordination.service.BrandService;
import com.musinsa.coordination.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    private final BrandService brandService;

    @GetMapping("/api/products")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> productList  = productService.findAllUseProduct()
                .stream()
                .map(ProductDto::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(productList);
    }

    @PostMapping("/api/product")
    public ResponseEntity<Boolean> insertProduct(@RequestBody @Valid ProductInsertRequest productInsertRequest,
                                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new BindingResultException(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        Brand brand = brandService.findBrand(productInsertRequest.getBrandId());

        productService.insertProduct(productInsertRequest.getProductName(),
                brand,
                productInsertRequest.getCategory(),
                productInsertRequest.getPrice());

        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PutMapping("/api/product")
    public ResponseEntity<Boolean> updateProduct(@RequestBody @Valid ProductInsertRequest productInsertRequest,
                                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new BindingResultException(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        Brand brand = brandService.findBrand(productInsertRequest.getBrandId());

        productService.updateProduct(productInsertRequest.getProductId(),
                productInsertRequest.getProductName(),
                brand,
                productInsertRequest.getCategory(),
                productInsertRequest.getPrice());

        return ResponseEntity.ok(Boolean.TRUE);
    }

    @DeleteMapping("/api/product")
    public ResponseEntity<Boolean> deleteProduct(@RequestBody long productId) {
        productService.deleteProduct(productId);

        return ResponseEntity.ok(Boolean.TRUE);
    }
}
