package com.example.demo.service;

import com.example.demo.persistence.entity.Book;
import com.example.demo.persistence.model.AuthorModel;
import com.example.demo.persistence.repository.JpaAuthorRepository;
import com.example.demo.service.impl.AuthorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @InjectMocks
    private AuthorServiceImpl subject;

    private AuthorModel author;
    private List<AuthorModel> authors;

    @Mock
    private JpaAuthorRepository authorRepo;

    @BeforeEach
    void setUp() {
        author = new AuthorModel();
        author.setId(1L);
        author.setFirstname("Heinrich");
        author.setLastname("Böll");
        authors = List.of(author);
    }

    @Test
    void shouldReturnAllAuthors() {
        //given

        //when
        when(authorRepo.findAllAuthors()).thenReturn(authors);

        var actual = subject.getAllAuthors();

        //then
        assertThat(actual).containsAll(authors)
                .isNotEmpty();
    }

    @Test
    void shouldReturnNothingWhenAuthorsListIsEmpty() {
        //given
        authors = Collections.emptyList();

        //when
        when(authorRepo.findAllAuthors()).thenReturn(authors);

        var actual = subject.getAllAuthors();

        //then
        assertThat(actual).isEmpty();
    }

    @Test
    void shouldReturnAuthorById() {
        //given

        //when
        when(authorRepo.findAuthorById(author.getId())).thenReturn(author);

        var actual = subject.getAuthor(author.getId());

        //then
        assertThat(actual).isEqualTo(author)
                .isNotNull();
    }

    @Test
    void shouldReturnAuthorsListByBookId() {
        //given
        Book book = new Book();
        book.setId(1L);
        book.setTitle("The Clown");
        //when
        when(authorRepo.findAuthorByBookId(book.getId())).thenReturn(authors);

        var actual = subject.getAuthorsByBook(book.getId());

        //then
        assertThat(actual).containsAll(authors)
                .isNotEmpty();
    }

    @Test
    void shouldReturnNothingWhenAuthorsListByBookIdIsEmpty() {
        //given
        authors = Collections.emptyList();

        Book book = new Book();
        book.setId(1L);
        book.setTitle("The Clown");

        //when
        when(authorRepo.findAuthorByBookId(book.getId())).thenReturn(authors);

        var actual = subject.getAuthorsByBook(book.getId());

        //then
        assertThat(actual).isEmpty();
    }

    @Test
    void shouldCallRepository() {
        //given

        //when
        subject.saveAuthor(author);

        //then
        verify(authorRepo).save(author);
    }
}