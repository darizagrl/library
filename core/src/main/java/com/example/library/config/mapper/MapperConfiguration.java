package com.example.library.config.mapper;

import com.example.library.api.dto.AuthorDTO;
import com.example.library.api.dto.BookDTO;
import com.example.library.persistence.entity.Author;
import com.example.library.persistence.entity.Book;
import com.example.library.persistence.model.AuthorModel;
import com.example.library.persistence.model.BookModel;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperConfiguration extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Author.class, AuthorModel.class)
                .byDefault()
                .register();
        factory.classMap(Book.class, BookModel.class)
                .byDefault()
                .register();

        factory.classMap(AuthorModel.class, AuthorDTO.class)
                .byDefault()
                .register();
        factory.classMap(BookModel.class, BookDTO.class)
                .byDefault()
                .register();

    }
}
