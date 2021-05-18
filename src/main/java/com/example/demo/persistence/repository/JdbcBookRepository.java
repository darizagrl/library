package com.example.demo.persistence.repository;

import com.example.demo.persistence.model.BookModel;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcBookRepository implements BookRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<BookModel> findAllBooks() {
        final var sqlFindAll = "SELECT * FROM BOOKS";
        return jdbcTemplate.query(
                sqlFindAll,
                (rs, rowNum) ->
                        BookModel.builder()
                                .id(rs.getLong("id"))
                                .title(rs.getString("title"))
                                .build());
    }

    @Override
    public BookModel findBookById(Long id) {
        final var sqlFindById = "SELECT * FROM BOOKS WHERE id = ?";
        return jdbcTemplate.queryForObject(
                sqlFindById, (rs, rowNum) ->
                        BookModel.builder()
                                .id(rs.getLong("id"))
                                .title(rs.getString("title"))
                                .build(), id);
    }

    @Override
    public List<BookModel> findBookByAuthorId(Long authorId) {
        final var sqlFindAuthorByBookId = "SELECT * FROM authors au JOIN books_authors ba ON ba.author_id = au.id WHERE ba.book_id = ?";
        return jdbcTemplate.query(
                sqlFindAuthorByBookId,
                (rs, rowNum) ->
                        BookModel.builder()
                                .id(rs.getLong("id"))
                                .title(rs.getString("title"))
                                .build(), authorId);
    }
}
