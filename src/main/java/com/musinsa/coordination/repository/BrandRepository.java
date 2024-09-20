package com.musinsa.coordination.repository;

import com.musinsa.coordination.domain.Brand;
import com.musinsa.coordination.type.UseYn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> findAllByUseYnEquals(UseYn useYn);
}
