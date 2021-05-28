package com.example.library.config.mapper;

import com.example.library.api.dto.AuthorDTO;
import com.example.library.persistence.entity.Author;
import com.example.library.persistence.model.AuthorModel;
import ma.glasnost.orika.MapperFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MapperConfigurationTest {
    @Autowired
    private MapperFacade subject;
    private AuthorDTO authorDTO;
    private AuthorModel authorModel;


    @BeforeEach
    void setUp() {
        authorDTO = new AuthorDTO();
        authorDTO.setId(1L);
        authorDTO.setFirstname("Heinrich");
        authorDTO.setLastname("Böll");
    }

    @Test
    void shouldCallMapper() {
        //given

        //when
        authorModel = subject.map(authorDTO, AuthorModel.class);

        //then
        assertThat(authorDTO.getId()).isEqualTo(authorModel.getId());
        assertThat(authorDTO.getFirstname()).isEqualTo(authorModel.getFirstname());
        assertThat(authorDTO.getLastname()).isEqualTo(authorModel.getLastname());
    }

    @Test
    void shouldCallMapperToEntity() {
        //given
        authorModel = subject.map(authorDTO, AuthorModel.class);

        //when
        Author entity = subject.map(authorModel, Author.class);

        //then
        assertThat(entity.getId()).isEqualTo(authorModel.getId());
        assertThat(entity.getFirstname()).isEqualTo(authorModel.getFirstname());
        assertThat(entity.getLastname()).isEqualTo(authorModel.getLastname());
    }
}