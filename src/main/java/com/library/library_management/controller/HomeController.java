package com.library.library_management.controller;

import com.library.library_management.service.AuthorService;
import com.library.library_management.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final AuthorService authorService;
    private final BookService bookService;

    public HomeController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("authorCount", authorService.findAll().size());
        model.addAttribute("bookCount", bookService.findAll().size());
        model.addAttribute("activePage", "home");
        return "home";
    }
}
