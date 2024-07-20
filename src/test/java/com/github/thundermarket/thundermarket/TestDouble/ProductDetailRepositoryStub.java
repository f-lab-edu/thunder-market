package com.github.thundermarket.thundermarket.TestDouble;

import com.github.thundermarket.thundermarket.domain.ProductDetail;
import com.github.thundermarket.thundermarket.repository.ProductDetailRepository;
import java.util.Optional;

public class ProductDetailRepositoryStub implements ProductDetailRepository {

    @Override
    public <S extends ProductDetail> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ProductDetail> findByProductId(Long productId) {
        if (productId == 0) {
            return Optional.empty();
        }
        return Optional.of(new ProductDetail(
                1L,
                "white",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                0,
                "",
                ""
        ));
    }

    @Override
    public ProductDetail save(ProductDetail productDetail) {
        return null;
    }

    @Override
    public Optional<ProductDetail> findById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<ProductDetail> findAll() {
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
