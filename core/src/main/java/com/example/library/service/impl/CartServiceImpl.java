package com.example.library.service.impl;

import com.example.library.service.CartService;
import com.example.library.service.ShoppingCartVisitor;
import com.example.library.service.model.ItemElement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final ShoppingCartVisitor visitor;

    @Override
    public BigDecimal calculatePrice(List<ItemElement> items) {
        return items.stream()
                .map(item -> item.accept(visitor))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
