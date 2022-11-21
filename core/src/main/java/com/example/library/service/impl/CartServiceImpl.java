package com.example.library.service.impl;

import com.example.library.persistence.entity.Book;
import com.example.library.persistence.entity.Cart;
import com.example.library.persistence.repository.book_repo.BookRepository;
import com.example.library.persistence.repository.cart_repo.CartRepository;
import com.example.library.service.CartService;
import com.example.library.service.ShoppingCartVisitor;
import com.example.library.service.model.BookModel;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final ShoppingCartVisitor visitor;
    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final MapperFacade mapperFacade;

    @Override
    public BigDecimal calculatePrice() {
        List<BookModel> books = getBooksFromCart();
        return books.stream()
                .map(book -> book.accept(visitor))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void addToCart(String productId) {
        Long id = Long.parseLong(productId);
        BookModel bookModel = bookRepository.findBookById(id);
        Book book = mapperFacade.map(bookModel, Book.class);
        var cart = Cart.builder()
                .productId(id)
                .book(book)
                .build();
        cartRepository.save(cart);
    }

    private List<BookModel> getBooksFromCart() {
        List<Book> books = cartRepository.findAll()
                .stream()
                .map(Cart::getBook)
                .collect(Collectors.toList());
        return mapperFacade.mapAsList(books, BookModel.class);
    }

}
