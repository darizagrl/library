package com.example.library.persistence.repository.author_repo;

import com.example.library.persistence.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
    @Query(value = "SELECT * " +
            "FROM authors au " +
            "JOIN books_authors ba ON ba.author_id = au.id " +
            "WHERE ba.book_id = ?",
            nativeQuery = true)
    List<Author> findAuthorByBookId(Long id);
}