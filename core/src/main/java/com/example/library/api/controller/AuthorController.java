package com.example.library.api.controller;

import com.example.library.api.dto.AuthorDTO;
import com.example.library.api.dto.BookDTO;
import com.example.library.service.model.AuthorModel;
import com.example.library.service.AuthorService;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final MapperFacade mapperFacade;

    @GetMapping()
    public List<AuthorDTO> getAuthorList() {
        var authors = authorService.getAllAuthors();
        return mapperFacade.mapAsList(authors, AuthorDTO.class);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void postAuthor(@RequestBody AuthorDTO authorDTO) {
        AuthorModel authorModel = mapperFacade.map(authorDTO, AuthorModel.class);
        authorService.saveAuthor(authorModel);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable final Long id) {
        authorService.deleteAuthor(id);
    }

    @GetMapping("/{id}")
    public AuthorDTO getAuthor(@PathVariable final Long id) {
        var author = authorService.getAuthor(id);
        return mapperFacade.map(author, AuthorDTO.class);
    }

    @GetMapping("/{id}/books")
    public List<BookDTO> getBooksByAuthor(@PathVariable final Long id) {
        var books = bookService.getAllBooksByAuthor(id);
        return mapperFacade.mapAsList(books, BookDTO.class);
    }
}
