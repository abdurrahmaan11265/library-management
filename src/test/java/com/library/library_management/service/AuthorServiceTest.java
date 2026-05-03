package com.library.library_management.service;

import com.library.library_management.entity.Author;
import com.library.library_management.exception.ResourceNotFoundException;
import com.library.library_management.repository.AuthorRepository;
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
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void findAll_returnsAllAuthors() {
        List<Author> authors = List.of(
                new Author("George Orwell", "British", "Novelist"),
                new Author("Toni Morrison", "American", "Novelist")
        );
        when(authorRepository.findAll()).thenReturn(authors);

        List<Author> result = authorService.findAll();

        assertEquals(2, result.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void findById_returnsAuthorWhenFound() {
        Author author = new Author("George Orwell", "British", "Novelist");
        author.setId(1L);
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Author result = authorService.findById(1L);

        assertNotNull(result);
        assertEquals("George Orwell", result.getName());
    }

    @Test
    void findById_throwsWhenNotFound() {
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> authorService.findById(99L));
    }

    @Test
    void save_persistsAndReturnsAuthor() {
        Author author = new Author("Haruki Murakami", "Japanese", "Surrealist author");
        when(authorRepository.save(author)).thenReturn(author);

        Author result = authorService.save(author);

        assertNotNull(result);
        assertEquals("Haruki Murakami", result.getName());
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    void update_modifiesExistingAuthor() {
        Author existing = new Author("Old Name", "Old Nationality", "Old bio");
        existing.setId(1L);
        Author updated = new Author("New Name", "New Nationality", "New bio");

        when(authorRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(authorRepository.save(existing)).thenReturn(existing);

        Author result = authorService.update(1L, updated);

        assertEquals("New Name", result.getName());
        assertEquals("New Nationality", result.getNationality());
        assertEquals("New bio", result.getBio());
    }
}
