package com.example.library.service;

import java.math.BigDecimal;

public interface CartService {

    BigDecimal calculatePrice();

    void addToCart(String productId);

    void emptyCart();

}
