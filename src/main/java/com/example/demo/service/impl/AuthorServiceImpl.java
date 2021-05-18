package com.example.demo.service.impl;

import com.example.demo.persistence.model.AuthorModel;
import com.example.demo.persistence.repository.AuthorRepository;
import com.example.demo.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepo;

    @Override
    public List<AuthorModel> getAllAuthors() {
        return authorRepo.findAllAuthors();
    }

    @Override
    public AuthorModel getAuthor(final Long id) {
        return authorRepo.findAuthorById(id);
    }

    @Override
    public List<AuthorModel> getAuthorsByBook(Long id) {
        return authorRepo.findAuthorByBookId(id);
    }

    @Override
    public void saveAuthor(AuthorModel author) {
        authorRepo.save(author);
    }
}
