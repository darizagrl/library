package com.example.demo.api.controller;

import com.example.demo.api.dto.AuthorDTO;
import com.example.demo.api.dto.BookDTO;
import com.example.demo.service.AuthorService;
import com.example.demo.service.impl.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookServiceImpl bookService;
    private final AuthorService authorService;
    private final MapperFacade mapperFacade;

    @GetMapping()
    public List<BookDTO> getBookList() {
        var books = bookService.getAllBooks();
        return mapperFacade.mapAsList(books, BookDTO.class);
    }

    @GetMapping("/{id}")
    public BookDTO getBook(@PathVariable final Long id) {
        var book = bookService.getBook(id);
        return mapperFacade.map(book, BookDTO.class);
    }

    @GetMapping("/{id}/authors")
    public List<AuthorDTO> getAuthorsByBook(@PathVariable final Long id) {
        var authors = authorService.getAuthorsByBook(id);
        return mapperFacade.mapAsList(authors, AuthorDTO.class);
    }
}
