package com.example.demo.service.impl;

import com.example.demo.persistence.model.BookModel;
import com.example.demo.persistence.repository.BookRepository;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepo;

    @Override
    public List<BookModel> getAllBooks() {
        return bookRepo.findAllBooks();
    }

    @Override
    public BookModel getBook(Long id) {
        return bookRepo.findBookById(id);
    }

    @Override
    public List<BookModel> getAllBooksByAuthor(Long id) {
        return bookRepo.findBookByAuthorId(id);
    }
}
