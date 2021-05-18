package com.example.demo;

import com.example.demo.api.controller.AuthorController;
import com.example.demo.api.controller.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private AuthorController authorController;
    @Autowired
    private BookController bookController;

    @Test
    void contextLoads() {
        //given

        //when

        //then
        assertNotNull(authorController);
        assertNotNull(bookController);
    }

}
