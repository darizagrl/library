package com.example.library.persistence.repository.author_repo;

import com.example.library.config.RequestContext;
import com.example.library.service.model.AuthorModel;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Primary
public class AuthorRepoStrategy implements AuthorRepository {
    static final Logger logger = Logger.getLogger(AuthorRepoStrategy.class);
    private final JdbcAuthorRepository jdbcAuthorRepository;
    private final JpaAuthorRepository jpaAuthorRepository;
    @Autowired
    private RequestContext requestContext;

    @Override
    public List<AuthorModel> findAllAuthors() {
        if (doesHeaderContainJdbc()) {
            logger.info("Using jdbcTemplate");
            return jdbcAuthorRepository.findAllAuthors();
        }
        logger.info("Using jpa");
        return jpaAuthorRepository.findAllAuthors();
    }

    @Override
    public AuthorModel findAuthorById(Long id) {
        if (doesHeaderContainJdbc()) {
            logger.info("Using jdbcTemplate");
            return jdbcAuthorRepository.findAuthorById(id);
        }
        logger.info("Using jpa");
        return jpaAuthorRepository.findAuthorById(id);
    }

    @Override
    public List<AuthorModel> findAuthorByBookId(Long id) {
        if (doesHeaderContainJdbc()) {
            logger.info("Using jdbcTemplate");
            return jdbcAuthorRepository.findAuthorByBookId(id);
        }
        logger.info("Using jpa");
        return jpaAuthorRepository.findAuthorByBookId(id);
    }

    @Override
    public void save(AuthorModel authorModel) {
        if (doesHeaderContainJdbc()) {
            logger.info("Using jdbcTemplate");
            jdbcAuthorRepository.save(authorModel);
        }
        logger.info("Using jpa");
        jpaAuthorRepository.save(authorModel);
    }

    @Override
    public void delete(Long id) {
        if (doesHeaderContainJdbc()) {
            logger.info("Using jdbcTemplate");
            jdbcAuthorRepository.delete(id);
        }
        logger.info("Using jpa");
        jpaAuthorRepository.delete(id);
    }

    private boolean doesHeaderContainJdbc() {
        final String jdbcHeaderValue = requestContext.getJdbcHeaderValue();
        return jdbcHeaderValue != null && jdbcHeaderValue.contains("jdbc");
    }
}
