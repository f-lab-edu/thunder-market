package com.github.thundermarket.thundermarket.testDouble;

import com.github.thundermarket.thundermarket.domain.ProductDetail;
import com.github.thundermarket.thundermarket.repository.ProductDetailRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDetailFakeRepository implements ProductDetailRepository {

    private final List<ProductDetail> productDetails = new ArrayList<>();

    @Override
    public ProductDetail save(ProductDetail productDetail) {
        productDetails.add(productDetail);
        return productDetail;
    }

    @Override
    public ProductDetail findByProductId(Long productId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<ProductDetail> findById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends ProductDetail> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<ProductDetail> findAll() {
        System.out.println("HELLO?");
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<ProductDetail> findAllById(Iterable<Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(ProductDetail entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends ProductDetail> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }
}

