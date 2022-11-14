package com.example.library.persistence.repository.author_repo;

import com.example.library.service.model.AuthorModel;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcAuthorRepository implements AuthorRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<AuthorModel> findAllAuthors() {
        final var sqlFindAll = "SELECT * FROM AUTHORS";
        return jdbcTemplate.query(
                sqlFindAll,
                (rs, rowNum) ->
                        AuthorModel.builder()
                                .id(rs.getLong("id"))
                                .firstname(rs.getString("firstname"))
                                .lastname(rs.getString("lastname")).build());
    }

    @Override
    public AuthorModel findAuthorById(Long id) {
        final var sqlFindById = "SELECT * FROM AUTHORS WHERE id = ?";
        return jdbcTemplate.queryForObject(
                sqlFindById, (rs, rowNum) ->
                        AuthorModel.builder()
                                .id(rs.getLong("id"))
                                .firstname(rs.getString("firstname"))
                                .lastname(rs.getString("lastname"))
                                .build(), id);
    }

    @Override
    public List<AuthorModel> findAuthorByBookId(Long bookId) {
        final var sqlFindAuthorByBookId = "SELECT * FROM authors au JOIN books_authors ba ON ba.author_id = au.id WHERE ba.book_id = ?";
        return jdbcTemplate.query(
                sqlFindAuthorByBookId,
                (rs, rowNum) ->
                        AuthorModel.builder()
                                .id(rs.getLong("id"))
                                .firstname(rs.getString("firstname"))
                                .lastname(rs.getString("lastname"))
                                .build(), bookId);
    }

    @Override
    public void save(AuthorModel authorModel) {
        final var sqlSave = "INSERT INTO AUTHORS (FIRSTNAME, LASTNAME) values(?,?)";
        jdbcTemplate.update(
                sqlSave,
                authorModel.getFirstname(), authorModel.getLastname());
    }

    @Override
    public void delete(Long id) {
        final var sqlDelete = "DELETE FROM AUTHORS WHERE ID = ?";
        jdbcTemplate.update(sqlDelete, id);
    }
}
