package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.Author;
import com.example.demo.persistence.model.AuthorModel;
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