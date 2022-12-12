package com.example.library.service.visitor;

import com.example.library.persistence.entity.Book;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
public class BookDiscount extends ShoppingCartDecorator {

    public BookDiscount(ShoppingCartVisitor visitor) {
        super(visitor);
    }

    @Override
    public BigDecimal visit(Book book) {
        if (Objects.nonNull(book.getDiscount())) {
            log.info("Applying book discount");
            return book.getPrice().multiply(book.getDiscount());
        }
        return book.getPrice();
    }
}

