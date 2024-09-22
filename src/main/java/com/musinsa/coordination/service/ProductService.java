package com.musinsa.coordination.service;


import com.musinsa.coordination.domain.Brand;
import com.musinsa.coordination.domain.Product;
import com.musinsa.coordination.exception.ProductNotFoundException;
import com.musinsa.coordination.repository.ProductRepository;
import com.musinsa.coordination.type.Category;
import com.musinsa.coordination.type.UseYn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAllUseProduct() {

        return productRepository.findAllByBrand_UseYn(UseYn.Y);
    }

    public void insertProduct(Brand brand,
                              String productName,
                              Category category,
                              long price) {

        Product product = Product.createProduct(productName, brand, category, price);
        productRepository.save(product);
    }

    public void updateProduct(Brand brand,
                              Long productId,
                              String productName,
                              Category category,
                              long price) {

        Product product = this.findProduct(productId);
        product.changeProduct(productName, brand, category, price);

        productRepository.save(product);
    }

    public void deleteProduct(long productId) {

        Product product = this.findProduct(productId);

        productRepository.delete(product);
    }

    private Product findProduct(long productId) {

        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("상품 정보가 잘못 되었습니다."));
    }
}
