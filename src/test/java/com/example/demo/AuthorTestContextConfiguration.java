package com.example.demo;

import com.example.demo.config.mapper.MapperConfiguration;
import ma.glasnost.orika.MapperFacade;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class AuthorTestContextConfiguration {
    @Bean
    public MapperFacade mapperFacade() {
        return new MapperConfiguration() {
        };
    }
}
