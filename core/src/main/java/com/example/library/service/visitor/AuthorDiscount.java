package com.example.library.service.visitor;

import com.example.library.persistence.entity.Author;
import com.example.library.persistence.entity.Book;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
public class AuthorDiscount extends ShoppingCartDecorator {

    public AuthorDiscount(ShoppingCartVisitor visitor) {
        super(visitor);
    }

    @Override
    public BigDecimal visit(Book book) {
        log.info("Applying author discount");

        return book.getAuthors()
                .stream()
                .map(Author::getDiscount)
                .filter(Objects::nonNull)
                .reduce(book.getPrice(), BigDecimal::multiply);
    }
}
