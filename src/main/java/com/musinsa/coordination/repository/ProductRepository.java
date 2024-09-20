package com.musinsa.coordination.repository;

import com.musinsa.coordination.domain.Product;
import com.musinsa.coordination.type.Category;
import com.musinsa.coordination.type.UseYn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByBrand_UseYn(UseYn useYn);

    List<Product> findByCategoryEqualsAndBrand_UseYnOrderByProductIdAsc(Category category, UseYn useYn);
}
