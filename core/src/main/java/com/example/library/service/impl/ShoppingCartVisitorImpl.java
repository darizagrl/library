package com.example.library.service.impl;

import com.example.library.service.ShoppingCartVisitor;
import com.example.library.service.model.BookModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ShoppingCartVisitorImpl implements ShoppingCartVisitor {

    @Override
    public BigDecimal visit(BookModel book) {
        return book.getPrice();
    }
}
