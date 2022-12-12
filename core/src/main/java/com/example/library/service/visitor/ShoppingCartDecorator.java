package com.example.library.service.visitor;

import com.example.library.persistence.entity.Book;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class ShoppingCartDecorator implements ShoppingCartVisitor {

    private final ShoppingCartVisitor visitor;

    @Override
    public BigDecimal visit(Book book) {
        return visitor.visit(book);
    }
}
