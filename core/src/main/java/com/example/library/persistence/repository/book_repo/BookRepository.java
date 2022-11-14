package com.example.library.persistence.repository.book_repo;

import com.example.library.service.model.BookModel;

import java.util.List;

public interface BookRepository {
    List<BookModel> findAllBooks();

    BookModel findBookById(Long id);

    List<BookModel> findBookByAuthorId(Long id);
}
