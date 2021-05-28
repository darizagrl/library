package com.example.library.api.controller;

import com.example.library.api.dto.AuthorDTO;
import com.example.library.api.dto.BookDTO;
import com.example.library.persistence.model.AuthorModel;
import com.example.library.persistence.model.BookModel;
import com.example.library.service.impl.AuthorServiceImpl;
import com.example.library.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class BookEndpointTest {
    BookDTO bookDTO;
    BookModel bookModel;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private AuthorServiceImpl authorService;
    @MockBean
    private BookServiceImpl bookService;
    @Autowired
    private JacksonTester<AuthorDTO> jsonAuthor;
    @Autowired
    private JacksonTester<BookDTO> jsonBook;

    @BeforeEach
    void setUp() {
        bookModel = new BookModel();
        bookModel.setId(2L);
        bookModel.setTitle("A Streetcar Named Desire");

        bookDTO = new BookDTO();
        bookDTO.setId(2L);
        bookDTO.setTitle("A Streetcar Named Desire");
    }

    @Test
    void shouldContainAllBooks() throws Exception {
        //given
        given(bookService.getAllBooks())
                .willReturn(List.of(bookModel));
        //when
        MockHttpServletResponse response = mvc.perform(
                get("/books")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(
                jsonBook.write(bookDTO).getJson()
        );
    }

    @Test
    void shouldContainBookTheClown() throws Exception {
        //given
        given(bookService.getBook(1L))
                .willReturn(bookModel);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/books/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonBook.write(bookDTO).getJson()
        );
    }

    @Test
    void shouldContainBookAuthorTennesseeWilliams() throws Exception {
        //given
        AuthorModel authorModel = new AuthorModel();
        authorModel.setId(2L);
        authorModel.setFirstname("Tennessee");
        authorModel.setLastname("Williams");

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(2L);
        authorDTO.setFirstname("Tennessee");
        authorDTO.setLastname("Williams");

        given(authorService.getAuthorsByBook(2L))
                .willReturn(List.of(authorModel));
        //when
        MockHttpServletResponse response = mvc.perform(
                get("/books/2/authors")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(
                jsonAuthor.write(authorDTO).getJson()
        );
    }
}