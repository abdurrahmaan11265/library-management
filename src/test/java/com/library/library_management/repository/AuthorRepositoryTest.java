package com.library.library_management.repository;

import com.library.library_management.entity.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void save_andFindById_works() {
        Author author = new Author("Virginia Woolf", "British", "Modernist author");
        Author saved = authorRepository.save(author);

        Optional<Author> found = authorRepository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("Virginia Woolf", found.get().getName());
    }

    @Test
    void findAll_returnsAllSavedAuthors() {
        authorRepository.save(new Author("Isabel Allende", "Chilean", "Magical realist"));
        authorRepository.save(new Author("Salman Rushdie", "British-Indian", "Postmodern novelist"));

        List<Author> authors = authorRepository.findAll();

        assertTrue(authors.size() >= 2);
    }

    @Test
    void existsByName_returnsTrueForExistingName() {
        authorRepository.save(new Author("Fyodor Dostoevsky", "Russian", "Psychological novelist"));

        assertTrue(authorRepository.existsByName("Fyodor Dostoevsky"));
    }

    @Test
    void existsByName_returnsFalseForUnknownName() {
        assertFalse(authorRepository.existsByName("Unknown Author XYZ"));
    }

    @Test
    void save_andUpdate_persistsChanges() {
        Author author = authorRepository.save(new Author("Draft Name", "Unknown", "Draft bio"));
        author.setName("Final Name");
        author.setNationality("British");
        Author updated = authorRepository.save(author);

        assertEquals("Final Name", updated.getName());
        assertEquals("British", updated.getNationality());
    }
}
