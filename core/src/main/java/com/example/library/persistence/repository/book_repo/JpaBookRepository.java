package com.example.library.persistence.repository.book_repo;

import com.example.library.service.model.BookModel;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaBookRepository implements BookRepository {
    private final MapperFacade mapperFacade;
    private final BookRepo bookRepo;

    @Override
    public List<BookModel> findAllBooks() {
        var books = bookRepo.findAll();
        return mapperFacade.mapAsList(books, BookModel.class);
    }

    @Override
    public BookModel findBookById(Long id) {
        var book = bookRepo.findById(id).orElse(null);
        return mapperFacade.map(book, BookModel.class);
    }

    @Override
    public List<BookModel> findBookByAuthorId(Long id) {
        var books = bookRepo.findBooksByAuthor(id);
        return mapperFacade.mapAsList(books, BookModel.class);
    }
}
