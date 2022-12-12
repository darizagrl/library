package com.example.library.service.impl;

import com.example.library.persistence.entity.Book;
import com.example.library.service.visitor.ShoppingCartVisitor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ShoppingCartVisitorImpl implements ShoppingCartVisitor {

    @Override
    public BigDecimal visit(Book book) {
        return book.getPrice();
    }
}
