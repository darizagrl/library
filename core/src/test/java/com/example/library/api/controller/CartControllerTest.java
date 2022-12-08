package com.example.library.api.controller;

import com.example.library.service.CartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CartControllerTest {

    @InjectMocks
    private CartController subject;

    @Mock
    private CartService cartService;

    @Test
    void shouldAddProductToCartWhenReceivesId() {
        //given
        String productId = "1";
        //when
        subject.addToCart(productId);

        //then
        verify(cartService).addToCart(productId);
    }

    @Test
    void shouldReturnTotalCostWhenCheckout() {
        //given
        BigDecimal totalCost = new BigDecimal("19.90");
        given(cartService.calculatePrice()).willReturn(totalCost);
        //when
        var responseEntity = subject.checkout();

        //then
        assertThat(responseEntity.getBody()).isEqualTo(totalCost);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
