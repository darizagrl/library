package com.example.demo.service;

import com.example.demo.persistence.model.AuthorModel;

import java.util.List;

public interface AuthorService {
    List<AuthorModel> getAllAuthors();

    AuthorModel getAuthor(Long id);

    List<AuthorModel> getAuthorsByBook(Long id);

    void saveAuthor(AuthorModel author);
}
