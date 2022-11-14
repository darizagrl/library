package com.example.library.service;

import com.example.library.service.model.BookModel;

import java.math.BigDecimal;

public interface ShoppingCartVisitor {

    BigDecimal visit(BookModel book);
}
