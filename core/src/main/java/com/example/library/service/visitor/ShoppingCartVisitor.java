package com.example.library.service.visitor;

import com.example.library.persistence.entity.Book;

import java.math.BigDecimal;

public interface ShoppingCartVisitor {

    BigDecimal visit(Book book);
}
