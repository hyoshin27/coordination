package com.musinsa.coordination.controller.api;

import com.musinsa.coordination.model.request.BrandCreateRequest;
import com.musinsa.coordination.model.request.BrandDeleteRequest;
import com.musinsa.coordination.model.request.BrandUpdateRequest;
import com.musinsa.coordination.model.response.SuccessResponse;
import com.musinsa.coordination.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/api/brands")
    public ResponseEntity<List<BrandUpdateRequest>> getBrands() {

        List<BrandUpdateRequest> brandList = brandService.findAllUseBrand()
                .stream()
                .map(BrandUpdateRequest::toDto)
                .toList();

        return ResponseEntity.ok(brandList);
    }

    @PostMapping("/api/brand")
    public ResponseEntity<SuccessResponse> insertBrands(@RequestBody @Valid BrandCreateRequest brandCreateRequest) {

        brandService.insertBrand(brandCreateRequest.getBrandName());

        return ResponseEntity.ok(SuccessResponse.from("ok"));
    }

    @PutMapping("/api/brand")
    public ResponseEntity<SuccessResponse> updateBrand(@RequestBody @Valid BrandUpdateRequest brandUpdateRequest) {

        brandService.updateBrand(brandUpdateRequest.getBrandId(), brandUpdateRequest.getBrandName());

        return ResponseEntity.ok(SuccessResponse.from("ok"));
    }

    @DeleteMapping("/api/brand")
    public ResponseEntity<SuccessResponse> deleteBrand(@RequestBody @Valid BrandDeleteRequest brandDeleteRequest) {

        brandService.deleteBrand(brandDeleteRequest.getBrandId());

        return ResponseEntity.ok(SuccessResponse.from("ok"));
    }
}
