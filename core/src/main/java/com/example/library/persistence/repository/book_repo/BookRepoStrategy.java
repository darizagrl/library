package com.example.library.persistence.repository.book_repo;

import com.example.library.config.RequestContext;
import com.example.library.persistence.model.BookModel;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Primary
public class BookRepoStrategy implements BookRepository {
    static final Logger logger = Logger.getLogger(BookRepoStrategy.class);
    private final JdbcBookRepository jdbcBookRepository;
    private final JpaBookRepository jpaBookRepository;
    @Autowired
    private RequestContext requestContext;

    @Override
    public List<BookModel> findAllBooks() {
        if (doesHeaderContainJdbc()) {
            logger.info("Using jdbcTemplate");
            return jdbcBookRepository.findAllBooks();
        }
        logger.info("Using jpa");
        return jpaBookRepository.findAllBooks();
    }

    @Override
    public BookModel findBookById(Long id) {
        if (doesHeaderContainJdbc()) {
            logger.info("Using jdbcTemplate");
            return jdbcBookRepository.findBookById(id);
        }
        logger.info("Using jpa");
        return jpaBookRepository.findBookById(id);
    }

    @Override
    public List<BookModel> findBookByAuthorId(Long id) {
        if (doesHeaderContainJdbc()) {
            logger.info("Using jdbcTemplate");
            return jdbcBookRepository.findBookByAuthorId(id);
        }
        logger.info("Using jpa");
        return jpaBookRepository.findBookByAuthorId(id);
    }

    private boolean doesHeaderContainJdbc() {
        final String jdbcHeaderValue = requestContext.getJdbcHeaderValue();
        return jdbcHeaderValue != null && jdbcHeaderValue.contains("jdbc");
    }
}
