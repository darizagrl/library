package com.example.library.service.impl;

import com.example.library.persistence.entity.Author;
import com.example.library.persistence.entity.Book;
import com.example.library.persistence.entity.CartItem;
import com.example.library.persistence.repository.cart_repo.CartRepository;
import com.example.library.service.BookService;
import com.example.library.service.CartService;
import com.example.library.service.model.BookModel;
import com.example.library.service.visitor.AuthorDiscount;
import com.example.library.service.visitor.BookDiscount;
import com.example.library.service.visitor.ShoppingCartVisitor;
import com.example.library.service.visitor.ShoppingCartVisitorImpl;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final BookService bookService;
    private final MapperFacade mapperFacade;
    private ShoppingCartVisitor visitor;

    @Override
    public void addToCart(String productId) {
        Long id = Long.parseLong(productId);
        BookModel bookModel = bookService.getBook(id);
        Book book = mapperFacade.map(bookModel, Book.class);
        var cart = CartItem.builder()
                .productId(id)
                .book(book)
                .build();
        cartRepository.save(cart);
    }

    @Override
    public void emptyCart() {
        cartRepository.deleteAll();
    }

    @Override
    public BigDecimal calculatePrice() {
        List<Book> books = getBooksFromCart();
        BigDecimal price = BigDecimal.ZERO;
        for (Book book : books) {
            visitor = checkAuthorDiscount(book);
            price = price.add(book.accept(visitor));
        }
        return price;
    }

    private ShoppingCartVisitor checkBookDiscount(Book book) {
        if (Objects.nonNull(book.getDiscount())) {
            return new BookDiscount(visitor);
        }
        return new ShoppingCartVisitorImpl();
    }

    private ShoppingCartVisitor checkAuthorDiscount(Book book) {
        var authorsDiscount = book.getAuthors()
                .stream()
                .map(Author::getDiscount)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (!authorsDiscount.isEmpty()) {
           return new AuthorDiscount(visitor);
        }
        return checkBookDiscount(book);
    }

    private List<Book> getBooksFromCart() {
        return cartRepository.findAll()
                .stream()
                .map(CartItem::getBook)
                .collect(Collectors.toList());
    }
}
