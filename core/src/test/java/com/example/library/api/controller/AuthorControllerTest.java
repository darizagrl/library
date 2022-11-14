package com.example.library.api.controller;

import com.example.library.api.dto.AuthorDTO;
import com.example.library.service.model.AuthorModel;
import com.example.library.service.AuthorService;
import ma.glasnost.orika.MapperFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {

    @InjectMocks
    private AuthorController subject;

    @Mock
    private AuthorService authorService;
    @Mock
    private MapperFacade mapperFacade;

    @Test
    void shouldCallMapperFacade() {
        //given
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(1L);
        authorDTO.setFirstname("Tennessee");
        authorDTO.setLastname("Williams");

        //when
        subject.postAuthor(authorDTO);

        //then
        verify(mapperFacade).map(authorDTO, AuthorModel.class);
    }

    @Test
    void shouldCallAuthorService() {
        //given
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(1L);
        authorDTO.setFirstname("Tennessee");
        authorDTO.setLastname("Williams");
        AuthorModel authorModel = new AuthorModel();

        //when
        when(mapperFacade.map(authorDTO, AuthorModel.class)).thenReturn(authorModel);

        subject.postAuthor(authorDTO);

        //then
        verify(authorService).saveAuthor(authorModel);
    }
}