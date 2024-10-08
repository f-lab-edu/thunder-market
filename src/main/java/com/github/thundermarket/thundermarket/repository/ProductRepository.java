package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.domain.Product;
import com.github.thundermarket.thundermarket.dto.ProductFilterRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByIdGreaterThanOrderByIdDesc(Long cursorId, Pageable pageable);

    @Query("SELECT p FROM Product p JOIN p.productDetail pd " +
            "WHERE (:#{#productFilterRequest.name} IS NULL OR p.name = :#{#productFilterRequest.name}) " +
            "AND (:#{#productFilterRequest.priceMin} IS NULL OR p.price >= :#{#productFilterRequest.priceMin}) " +
            "AND (:#{#productFilterRequest.priceMax} IS NULL OR p.price <= :#{#productFilterRequest.priceMax}) " +
            "AND (:#{#productFilterRequest.color} IS NULL OR pd.color = :#{#productFilterRequest.color}) " +
            "AND (:#{#productFilterRequest.purchaseDateMin} IS NULL OR pd.purchaseDate >= :#{#productFilterRequest.purchaseDateMin}) " +
            "AND (:#{#productFilterRequest.purchaseDateMax} IS NULL OR pd.purchaseDate <= :#{#productFilterRequest.purchaseDateMax})")
    List<Product> filterByProductOptions(@Param("productFilterRequest") ProductFilterRequest productFilterRequest);

    List<Product> findByTitleContainingIgnoreCase(String keyword);

    List<Product> findByUserId(Long userId);
}
