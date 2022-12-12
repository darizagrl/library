package com.example.library.service.visitor;

import com.example.library.persistence.entity.Author;
import com.example.library.persistence.entity.Book;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class AuthorDiscount extends ShoppingCartDecorator {

    public AuthorDiscount(ShoppingCartVisitor visitor) {
        super(visitor);
    }

    @Override
    public BigDecimal visit(Book book) {
        log.info("Applying author discount");

        List<BigDecimal> discounts = book.getAuthors()
                .stream()
                .map(Author::getDiscount)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (!discounts.isEmpty()) {
            return discounts.stream()
                    .reduce(book.getPrice(), BigDecimal::multiply);
        }
        return book.getPrice();
    }
}
