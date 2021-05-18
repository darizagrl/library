package com.example.demo.service;

import com.example.demo.persistence.model.BookModel;

import java.util.List;

public interface BookService {
    List<BookModel> getAllBooks();

    BookModel getBook(Long id);

    List<BookModel> getAllBooksByAuthor(Long id);
}
