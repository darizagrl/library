package com.example.demo.service;

import com.example.demo.persistence.entity.Author;
import com.example.demo.persistence.repository.JpaBookRepository;
import com.example.demo.service.impl.BookServiceImpl;
import com.example.demo.persistence.model.BookModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @InjectMocks
    private BookServiceImpl subject;

    private BookModel book;
    private List<BookModel> books;

    @Mock
    private JpaBookRepository bookRepo;

    @BeforeEach
    void setUp() {
        book = new BookModel();
        book.setId(1L);
        book.setTitle("The Clown");
        books = List.of(book);
    }

    @Test
    void shouldReturnBookList() {
        //given

        //when
        when(bookRepo.findAllBooks()).thenReturn(books);

        var actual = subject.getAllBooks();

        //then
        assertThat(actual).isEqualTo(books)
                .isNotEmpty();
    }

    @Test
    void shouldReturnNothingWhenBooksListIsEmpty() {
        //given
        books = Collections.emptyList();

        //when
        when(bookRepo.findAllBooks()).thenReturn(books);

        var actual = subject.getAllBooks();

        //then
        assertThat(actual).isEmpty();
    }

    @Test
    void shouldReturnBookById() {
        //given

        //when
        when(bookRepo.findBookById(book.getId())).thenReturn(book);

        var actual = subject.getBook(book.getId());
        //then
        assertThat(actual).isEqualTo(book)
                .isNotNull();
    }

    @Test
    void getAllBooksByAuthor() {
        //given
        Author author = new Author();
        author.setId(1L);
        author.setFirstname("Heinrich");
        author.setLastname("Böll");

        //when
        when(bookRepo.findBookByAuthorId(author.getId())).thenReturn(books);

        var actual = subject.getAllBooksByAuthor(author.getId());
        //then
        assertThat(actual).containsAll(books)
                .isNotEmpty();
    }

    @Test
    void shouldReturnNothingWhenBooksListFoundByAuthorIdIsEmpty() {
        //given
        books = Collections.emptyList();

        Author author = new Author();
        author.setId(1L);
        author.setFirstname("Heinrich");
        author.setLastname("Böll");

        //when
        when(bookRepo.findBookByAuthorId(author.getId())).thenReturn(books);

        var actual = subject.getAllBooksByAuthor(author.getId());

        //then
        assertThat(actual).isEmpty();
    }
}