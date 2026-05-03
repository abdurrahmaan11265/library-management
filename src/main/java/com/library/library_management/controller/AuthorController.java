package com.library.library_management.controller;

import com.library.library_management.entity.Author;
import com.library.library_management.exception.ResourceNotFoundException;
import com.library.library_management.service.AuthorService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("activePage", "authors");
        return "authors/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("author", new Author());
        model.addAttribute("activePage", "authors");
        return "authors/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Author author, RedirectAttributes redirectAttrs) {
        try {
            authorService.save(author);
            redirectAttrs.addFlashAttribute("success", "Author added successfully.");
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("error", "Could not save author — please check for duplicate or missing fields.");
        }
        return "redirect:/authors";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttrs) {
        try {
            model.addAttribute("author", authorService.findById(id));
            model.addAttribute("activePage", "authors");
            return "authors/edit";
        } catch (ResourceNotFoundException e) {
            redirectAttrs.addFlashAttribute("error", e.getMessage());
            return "redirect:/authors";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Author author, RedirectAttributes redirectAttrs) {
        try {
            authorService.update(id, author);
            redirectAttrs.addFlashAttribute("success", "Author updated successfully.");
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("error", "Could not update author — data integrity violation.");
        } catch (ResourceNotFoundException e) {
            redirectAttrs.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/authors";
    }
}
