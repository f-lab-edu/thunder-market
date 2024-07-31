package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.domain.ProductDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDetailRepository extends CrudRepository<ProductDetail, Long> {
    ProductDetail findByProductId(Long productId);
}
