package com.library.library_management;

import com.library.library_management.entity.Author;
import com.library.library_management.entity.Book;
import com.library.library_management.repository.AuthorRepository;
import com.library.library_management.repository.BookRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataInitializer(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (authorRepository.count() > 0) {
            return;
        }

        Author a1  = authorRepository.save(new Author("George Orwell", "British", "English novelist and essayist known for his sharp political and social commentary."));
        Author a2  = authorRepository.save(new Author("Toni Morrison", "American", "Nobel Prize-winning novelist celebrated for her powerful portrayal of Black American life."));
        Author a3  = authorRepository.save(new Author("Gabriel García Márquez", "Colombian", "Father of magical realism, best known for One Hundred Years of Solitude."));
        Author a4  = authorRepository.save(new Author("Haruki Murakami", "Japanese", "Surrealist author blending Western pop culture with deeply Japanese settings."));
        Author a5  = authorRepository.save(new Author("Chimamanda Ngozi Adichie", "Nigerian", "Feminist author exploring identity, race, and postcolonial Nigeria."));
        Author a6  = authorRepository.save(new Author("Fyodor Dostoevsky", "Russian", "19th-century master of psychological fiction and moral philosophy."));
        Author a7  = authorRepository.save(new Author("Virginia Woolf", "British", "Modernist pioneer of the stream-of-consciousness narrative technique."));
        Author a8  = authorRepository.save(new Author("Kazuo Ishiguro", "British-Japanese", "Nobel laureate renowned for quiet, introspective novels about memory and loss."));
        Author a9  = authorRepository.save(new Author("Isabel Allende", "Chilean", "Author of sweeping family sagas blending history and magical realism."));
        Author a10 = authorRepository.save(new Author("Salman Rushdie", "British-Indian", "Postmodern novelist weaving myth, history, and magical realism."));

        bookRepository.save(new Book("Nineteen Eighty-Four", "Dystopian Fiction", 1949, a1));
        bookRepository.save(new Book("Beloved", "Historical Fiction", 1987, a2));
        bookRepository.save(new Book("One Hundred Years of Solitude", "Magical Realism", 1967, a3));
        bookRepository.save(new Book("Norwegian Wood", "Literary Fiction", 1987, a4));
        bookRepository.save(new Book("Purple Hibiscus", "Literary Fiction", 2003, a5));
        bookRepository.save(new Book("Crime and Punishment", "Psychological Fiction", 1866, a6));
        bookRepository.save(new Book("Mrs Dalloway", "Modernist Fiction", 1925, a7));
        bookRepository.save(new Book("The Remains of the Day", "Literary Fiction", 1989, a8));
        bookRepository.save(new Book("The House of the Spirits", "Magical Realism", 1982, a9));
        bookRepository.save(new Book("Midnight's Children", "Historical Fiction", 1981, a10));
    }
}
