package com.knf.dev.librarymanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.knf.dev.librarymanagementsystem.service.AuthorService;
import com.knf.dev.librarymanagementsystem.service.BookService;
import com.knf.dev.librarymanagementsystem.service.CategoryService;
import com.knf.dev.librarymanagementsystem.service.PublisherService;

@Controller
public class DashboardController {

	private final BookService bookService;
	private final AuthorService authorService;
	private final CategoryService categoryService;
	private final PublisherService publisherService;

	public DashboardController(BookService bookService, AuthorService authorService, CategoryService categoryService,
			PublisherService publisherService) {
		this.bookService = bookService;
		this.authorService = authorService;
		this.categoryService = categoryService;
		this.publisherService = publisherService;
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		model.addAttribute("totalBooks", bookService.countAll());
		model.addAttribute("totalAuthors", authorService.countAll());
		model.addAttribute("totalCategories", categoryService.countAll());
		model.addAttribute("totalPublishers", publisherService.countAll());
		model.addAttribute("recentBooks", bookService.findAllBooks());
		return "dashboard";
	}
}
