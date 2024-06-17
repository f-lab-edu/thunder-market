package com.github.thundermarket.thundermarket;

import com.github.thundermarket.thundermarket.domain.ProductDetail;
import com.github.thundermarket.thundermarket.exception.ResourceNotFoundException;
import com.github.thundermarket.thundermarket.repository.ProductDetailRepository;
import com.github.thundermarket.thundermarket.service.ProductDetailService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductDetailServiceTest {

    @Mock
    ProductDetailRepository productDetailRepository;

    @InjectMocks
    ProductDetailService productDetailService;

    private AutoCloseable autoCloseable;

    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void 상품0개_상세정보_조회() {
        Long productId = 1L;
        when(productDetailRepository.findById(productId)).thenReturn(null);

        Assertions.assertThatThrownBy(() -> productDetailService.getProductDetail(productId)).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void 상품1개_상세정보_조회() {
        String targetColor = "white";
        Long productId = 1L;
        ProductDetail productDetailForMock = new ProductDetail();
        productDetailForMock.setColor("white");
        when(productDetailRepository.findById(productId)).thenReturn(productDetailForMock);

        ProductDetail productDetail = productDetailService.getProductDetail(productId);

        verify(productDetailRepository).findById(productId);
        Assertions.assertThat(productDetail.getColor()).isEqualTo(targetColor);
    }
}
