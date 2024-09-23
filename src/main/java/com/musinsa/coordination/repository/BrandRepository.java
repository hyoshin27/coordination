package com.musinsa.coordination.repository;

import com.musinsa.coordination.domain.Brand;
import com.musinsa.coordination.type.UseYn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> findAllByUseYnEquals(UseYn useYn);

    Optional<Brand> findByBrandIdEqualsAndUseYnEquals(Long brandId, UseYn useYn);
}
