package com.github.thundermarket.thundermarket;

import com.github.thundermarket.thundermarket.TestDouble.ProductDetailRepositoryStub;
import com.github.thundermarket.thundermarket.domain.ProductDetail;
import com.github.thundermarket.thundermarket.domain.ProductDetailResponse;
import com.github.thundermarket.thundermarket.exception.ResourceNotFoundException;
import com.github.thundermarket.thundermarket.service.ProductDetailService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductDetailServiceTest {

    @Test
    public void 상품0개_상세정보_조회() {
        Long productId = 0L;
        ProductDetailService productDetailService = new ProductDetailService(new ProductDetailRepositoryStub());

        Assertions.assertThatThrownBy(() -> productDetailService.productDetail(productId)).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void 상품1개_상세정보_조회() {
        ProductDetailService productDetailService = new ProductDetailService(new ProductDetailRepositoryStub());
        ProductDetail productDetail = new ProductDetail("white", "", "", "", "", "", "", "", 0, "");
        ProductDetailResponse expectedProductDetail = ProductDetailResponse.of(productDetail);
        Long productId = 1L;

        ProductDetailResponse productDetailResponse = productDetailService.productDetail(productId);

        Assertions.assertThat(productDetailResponse).isEqualTo(expectedProductDetail);
    }
}
