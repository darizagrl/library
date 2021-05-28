package com.example.library.persistence.repository.book_repo;

import com.example.library.persistence.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * " +
            "FROM books bk " +
            "JOIN books_authors ba ON ba.book_id = bk.id " +
            "WHERE ba.author_id = ?",
            nativeQuery = true)
    List<Book> findBooksByAuthor(Long id);
}
