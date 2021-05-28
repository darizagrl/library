package com.example.library.persistence.repository.author_repo;

import com.example.library.persistence.model.AuthorModel;

import java.util.List;

public interface AuthorRepository {
    List<AuthorModel> findAllAuthors();

    AuthorModel findAuthorById(Long id);

    List<AuthorModel> findAuthorByBookId(Long id);

    void save(AuthorModel authorModel);

    void delete(Long id);
}
