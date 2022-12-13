package com.example.library.service.visitor;

import com.example.library.persistence.entity.Book;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ShoppingCartVisitorImpl implements ShoppingCartVisitor {

    @Override
    public BigDecimal visit(Book book) {
        return book.getPrice();
    }
}
