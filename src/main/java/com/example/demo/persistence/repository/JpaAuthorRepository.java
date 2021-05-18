package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.Author;
import com.example.demo.persistence.model.AuthorModel;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaAuthorRepository implements AuthorRepository {
    private final MapperFacade mapperFacade;
    private final AuthorRepo authorRepo;

    @Override
    public List<AuthorModel> findAllAuthors() {
        var authors = authorRepo.findAll();
        return mapperFacade.mapAsList(authors, AuthorModel.class);
    }

    @Override
    public AuthorModel findAuthorById(Long id) {
        var authors = authorRepo.findById(id).orElse(null);
        return mapperFacade.map(authors, AuthorModel.class);
    }

    @Override
    public List<AuthorModel> findAuthorByBookId(Long id) {
        var authors = authorRepo.findAuthorByBookId(id);
        return mapperFacade.mapAsList(authors, AuthorModel.class);
    }

    @Override
    public void save(AuthorModel authorModel) {
        Author entity = mapperFacade.map(authorModel, Author.class);
        authorRepo.save(entity);
    }
}
