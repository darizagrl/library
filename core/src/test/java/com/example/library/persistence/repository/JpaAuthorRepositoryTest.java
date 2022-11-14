package com.example.library.persistence.repository;

import com.example.library.persistence.entity.Author;
import com.example.library.service.model.AuthorModel;
import com.example.library.persistence.repository.author_repo.AuthorRepo;
import com.example.library.persistence.repository.author_repo.JpaAuthorRepository;
import ma.glasnost.orika.MapperFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JpaAuthorRepositoryTest {

    @InjectMocks
    private JpaAuthorRepository subject;

    @Mock
    private MapperFacade mapperFacade;

    @Mock
    private AuthorRepo authorRepo;

    @Test
    void shouldCallMapperFacade() {
        //given
        AuthorModel authorModel = new AuthorModel();

        //when
        subject.save(authorModel);

        //then
        verify(mapperFacade).map(authorModel, Author.class);
    }

    @Test
    void shouldCallAuthorRepo() {
        //given
        AuthorModel authorModel = new AuthorModel();
        Author entity = new Author();

        //when
        when(mapperFacade.map(authorModel, Author.class)).thenReturn(entity);

        subject.save(authorModel);

        //then
        verify(authorRepo).save(entity);
    }
}