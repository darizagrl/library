package com.example.demo.persistence.repository;

import com.example.demo.persistence.model.BookModel;

import java.util.List;

public interface BookRepository {
    List<BookModel> findAllBooks();

    BookModel findBookById(Long id);

    List<BookModel> findBookByAuthorId(Long id);
}
