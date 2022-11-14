package com.example.library.service;

import com.example.library.service.model.BookModel;

import java.util.List;

public interface BookService {
    List<BookModel> getAllBooks();

    BookModel getBook(Long id);

    List<BookModel> getAllBooksByAuthor(Long id);
}
