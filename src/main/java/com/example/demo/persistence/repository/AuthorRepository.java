package com.example.demo.persistence.repository;

import com.example.demo.persistence.model.AuthorModel;

import java.util.List;

public interface AuthorRepository {
    List<AuthorModel> findAllAuthors();

    AuthorModel findAuthorById(Long id);

    List<AuthorModel> findAuthorByBookId(Long id);

    void save(AuthorModel authorModel);
}
