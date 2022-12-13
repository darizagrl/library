package com.example.library.service.visitor;

import com.example.library.persistence.entity.Book;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class BookDiscount extends ShoppingCartDecorator {

    public BookDiscount(ShoppingCartVisitor visitor) {
        super(visitor);
    }

    @Override
    public BigDecimal visit(Book book) {
        log.info("Applying book discount");
        return book.getPrice().multiply(book.getDiscount());
    }
}

