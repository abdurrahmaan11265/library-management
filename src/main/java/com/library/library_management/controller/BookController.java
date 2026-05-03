package com.library.library_management.controller;

import com.library.library_management.entity.Author;
import com.library.library_management.entity.Book;
import com.library.library_management.exception.ResourceNotFoundException;
import com.library.library_management.service.AuthorService;
import com.library.library_management.service.BookService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", bookService.findAllWithAuthorDetails());
        model.addAttribute("activePage", "books");
        return "books/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("activePage", "books");
        return "books/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Book book,
                      @RequestParam Long authorId,
                      RedirectAttributes redirectAttrs) {
        try {
            Author author = authorService.findById(authorId);
            book.setAuthor(author);
            bookService.save(book);
            redirectAttrs.addFlashAttribute("success", "Book added successfully.");
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("error", "Could not save book — please check for missing or duplicate fields.");
        } catch (ResourceNotFoundException e) {
            redirectAttrs.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttrs) {
        try {
            model.addAttribute("book", bookService.findById(id));
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("activePage", "books");
            return "books/edit";
        } catch (ResourceNotFoundException e) {
            redirectAttrs.addFlashAttribute("error", e.getMessage());
            return "redirect:/books";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute Book book,
                         @RequestParam Long authorId,
                         RedirectAttributes redirectAttrs) {
        try {
            bookService.update(id, book, authorId);
            redirectAttrs.addFlashAttribute("success", "Book updated successfully.");
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("error", "Could not update book — data integrity violation.");
        } catch (ResourceNotFoundException e) {
            redirectAttrs.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/books";
    }
}
