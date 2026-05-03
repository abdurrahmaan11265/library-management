package com.library.library_management.repository;

import com.library.library_management.dto.BookSummary;
import com.library.library_management.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT new com.library.library_management.dto.BookSummary(" +
           "b.id, b.title, b.genre, b.publishedYear, a.id, a.name, a.nationality) " +
           "FROM Book b JOIN b.author a")
    List<BookSummary> findAllWithAuthorDetails();
}
