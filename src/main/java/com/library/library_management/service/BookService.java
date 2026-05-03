package com.library.library_management.service;

import com.library.library_management.dto.BookSummary;
import com.library.library_management.entity.Author;
import com.library.library_management.entity.Book;
import com.library.library_management.exception.ResourceNotFoundException;
import com.library.library_management.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<BookSummary> findAllWithAuthorDetails() {
        return bookRepository.findAllWithAuthorDetails();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public Book update(Long id, Book updated, Long authorId) {
        Book existing = findById(id);
        Author author = authorService.findById(authorId);
        existing.setTitle(updated.getTitle());
        existing.setGenre(updated.getGenre());
        existing.setPublishedYear(updated.getPublishedYear());
        existing.setAuthor(author);
        return bookRepository.save(existing);
    }
}
