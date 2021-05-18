package com.example.demo.api.dto;

import com.example.demo.persistence.model.BookModel;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class AuthorDTO {
    private Long id;
    private String firstname;
    private String lastname;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BookModel> booksList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<BookModel> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<BookModel> booksList) {
        this.booksList = booksList;
    }
}
