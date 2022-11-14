package com.example.library.service;

import com.example.library.service.model.AuthorModel;

import java.util.List;

public interface AuthorService {
    List<AuthorModel> getAllAuthors();

    AuthorModel getAuthor(Long id);

    List<AuthorModel> getAuthorsByBook(Long id);

    void saveAuthor(AuthorModel author);

    void deleteAuthor(Long id);
}
