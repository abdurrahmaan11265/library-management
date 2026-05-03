package com.library.library_management.service;

import com.library.library_management.entity.Author;
import com.library.library_management.entity.Book;
import com.library.library_management.exception.ResourceNotFoundException;
import com.library.library_management.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private BookService bookService;

    @Test
    void findAll_returnsAllBooks() {
        Author author = new Author("George Orwell", "British", "Novelist");
        List<Book> books = List.of(
                new Book("Nineteen Eighty-Four", "Dystopian Fiction", 1949, author),
                new Book("Animal Farm", "Political Satire", 1945, author)
        );
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.findAll();

        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void findById_returnsBookWhenFound() {
        Author author = new Author("Toni Morrison", "American", "Novelist");
        Book book = new Book("Beloved", "Historical Fiction", 1987, author);
        book.setId(1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book result = bookService.findById(1L);

        assertNotNull(result);
        assertEquals("Beloved", result.getTitle());
    }

    @Test
    void findById_throwsWhenNotFound() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> bookService.findById(99L));
    }

    @Test
    void save_persistsAndReturnsBook() {
        Author author = new Author("Haruki Murakami", "Japanese", "Surrealist author");
        Book book = new Book("Norwegian Wood", "Literary Fiction", 1987, author);
        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.save(book);

        assertNotNull(result);
        assertEquals("Norwegian Wood", result.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void update_modifiesExistingBook() {
        Author author = new Author("Kazuo Ishiguro", "British-Japanese", "Novelist");
        author.setId(1L);
        Book existing = new Book("Old Title", "Old Genre", 2000, author);
        existing.setId(1L);
        Book updated = new Book("The Remains of the Day", "Literary Fiction", 1989, null);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(authorService.findById(1L)).thenReturn(author);
        when(bookRepository.save(existing)).thenReturn(existing);

        Book result = bookService.update(1L, updated, 1L);

        assertEquals("The Remains of the Day", result.getTitle());
        assertEquals("Literary Fiction", result.getGenre());
        assertEquals(1989, result.getPublishedYear());
    }
}
