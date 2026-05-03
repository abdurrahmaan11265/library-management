package com.library.library_management.repository;

import com.library.library_management.dto.BookSummary;
import com.library.library_management.entity.Author;
import com.library.library_management.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private Author savedAuthor;

    @BeforeEach
    void setUp() {
        savedAuthor = authorRepository.save(new Author("George Orwell", "British", "Novelist"));
    }

    @Test
    void save_andFindById_works() {
        Book book = new Book("Nineteen Eighty-Four", "Dystopian Fiction", 1949, savedAuthor);
        Book saved = bookRepository.save(book);

        Optional<Book> found = bookRepository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("Nineteen Eighty-Four", found.get().getTitle());
    }

    @Test
    void findAllWithAuthorDetails_returnsInnerJoinResult() {
        bookRepository.save(new Book("Animal Farm", "Political Satire", 1945, savedAuthor));
        bookRepository.save(new Book("Nineteen Eighty-Four", "Dystopian Fiction", 1949, savedAuthor));

        List<BookSummary> summaries = bookRepository.findAllWithAuthorDetails();

        assertFalse(summaries.isEmpty());
        assertEquals(2, summaries.size());
        summaries.forEach(s -> {
            assertNotNull(s.getAuthorName());
            assertEquals("George Orwell", s.getAuthorName());
        });
    }

    @Test
    void findAllWithAuthorDetails_includesAuthorNationality() {
        bookRepository.save(new Book("Animal Farm", "Political Satire", 1945, savedAuthor));

        List<BookSummary> summaries = bookRepository.findAllWithAuthorDetails();

        assertEquals("British", summaries.get(0).getAuthorNationality());
    }

    @Test
    void save_andUpdate_persistsChanges() {
        Book book = bookRepository.save(new Book("Draft Title", "Draft Genre", 2000, savedAuthor));
        book.setTitle("Final Title");
        book.setGenre("Literary Fiction");
        Book updated = bookRepository.save(book);

        assertEquals("Final Title", updated.getTitle());
        assertEquals("Literary Fiction", updated.getGenre());
    }
}
