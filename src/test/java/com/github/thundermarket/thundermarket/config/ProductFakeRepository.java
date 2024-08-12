package com.github.thundermarket.thundermarket.config;

import com.github.thundermarket.thundermarket.constant.ProductStatus;
import com.github.thundermarket.thundermarket.domain.Product;
import com.github.thundermarket.thundermarket.dto.ProductFilterRequest;
import com.github.thundermarket.thundermarket.repository.ProductRepository;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.github.thundermarket.thundermarket.config.TestUtils.createProduct;

public class ProductFakeRepository implements ProductRepository {

    private final List<Product> products = new ArrayList<>();

    @Override
    public List<Product> findByIdGreaterThanOrderByIdDesc(Long cursorId, Pageable pageable) {
        return products;
    }

    @Override
    public Product save(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(product.getId())) {
                products.set(i, product);
                return product;
            }
        }
        products.add(product);
        return product;
    }

    @Override
    public List<Product> filterByProductOptions(ProductFilterRequest productFilterRequest) {
        save(createProduct(null, "아이폰 팝니다", "iPhone11", 290_000, ProductStatus.AVAILABLE, 1L));

        return products.stream()
                .filter(p -> p.getName().equals(productFilterRequest.getName())
                        && p.getPrice() >= productFilterRequest.getPriceMin()
                        && p.getPrice() <= productFilterRequest.getPriceMax())
                .toList();
    }

    @Override
    public List<Product> findByTitleContainingIgnoreCase(String keyword) {
        return products.stream()
                .filter(p -> p.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    @Override
    public List<Product> findByUserId(Long userId) {
        return products.stream()
                .filter(p -> p.getUserId().equals(userId))
                .toList();
    }

    @Override
    public <S extends Product> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Product> findById(Long aLong) {
        return products.stream().filter(p -> p.getId().equals(aLong)).findFirst();
    }

    @Override
    public boolean existsById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Product> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Product> findAllById(Iterable<Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Long id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                products.remove(i);
            }
        }
    }

    @Override
    public void delete(Product entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends Product> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }
}
