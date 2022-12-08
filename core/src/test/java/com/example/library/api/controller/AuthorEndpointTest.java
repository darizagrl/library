package com.example.library.api.controller;

import com.example.library.api.dto.AuthorDTO;
import com.example.library.api.dto.BookDTO;
import com.example.library.persistence.entity.Author;
import com.example.library.persistence.entity.Book;
import com.example.library.persistence.repository.author_repo.AuthorRepo;
import com.example.library.persistence.repository.book_repo.BookRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ExtendWith(MockitoExtension.class)
class AuthorEndpointTest {
    @Captor
    ArgumentCaptor<Author> authorCaptor;
    AuthorDTO authorDTO;
    BookDTO bookDTO;
    long id;
    String firstname;
    String lastname;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private AuthorRepo authorRepo;
    @MockBean
    private BookRepo bookRepo;
    @Autowired
    private JacksonTester<AuthorDTO> jsonAuthor;
    @Autowired
    private JacksonTester<BookDTO> jsonBook;

    @BeforeEach
    void setUp() {
        id = 1L;
        firstname = "Tennessee";
        lastname = "Williams";

        authorDTO = new AuthorDTO();
        authorDTO.setId(id);
        authorDTO.setFirstname(firstname);
        authorDTO.setLastname(lastname);
    }

    @Test
    void shouldCreateNewAuthor() throws Exception {
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonAuthorBody = objectMapper.writeValueAsString(authorDTO);

        //when
        mvc.perform(post("/authors").contentType(APPLICATION_JSON)
                .content(jsonAuthorBody))
                .andExpect(status().isCreated());

        verify(authorRepo).save(authorCaptor.capture());
        Author author = authorCaptor.getValue();

        //then
        assertThat(author.getId()).isEqualTo(id);
        assertThat(author.getFirstname()).isEqualTo(firstname);
        assertThat(author.getLastname()).isEqualTo(lastname);
    }

    @Test
    void shouldContainAllAuthors() throws Exception {
        //given
        Author author = new Author();
        author.setId(id);
        author.setFirstname(firstname);
        author.setLastname(lastname);

        given(authorRepo.findAll())
                .willReturn(List.of(author));
        //when
        MockHttpServletResponse response = mvc.perform(
                get("/authors")
                        .accept(APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(
                jsonAuthor.write(authorDTO).getJson());
    }

    @Test
    void shouldContainAuthorBooks() throws Exception {
        //given
        Book book = new Book();
        book.setId(2L);
        book.setTitle("A Streetcar Named Desire");

        bookDTO = new BookDTO();
        bookDTO.setId(2L);
        bookDTO.setTitle("A Streetcar Named Desire");

        given(bookRepo.findBooksByAuthor(2L))
                .willReturn(List.of(book));
        //when
        MockHttpServletResponse response = mvc.perform(
                get("/authors/2/books")
                        .accept(APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(
                jsonBook.write(bookDTO).getJson()
        );
    }

    @Test
    void shouldContainAuthorTennesseeWilliams() throws Exception {
        //given
        Author author = new Author();
        author.setId(id);
        author.setFirstname(firstname);
        author.setLastname(lastname);
        given(authorRepo.findById(id))
                .willReturn(Optional.of(author));

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/authors/1")
                        .accept(APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonAuthor.write(authorDTO).getJson()
        );
    }

}