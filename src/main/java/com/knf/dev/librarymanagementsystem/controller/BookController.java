package com.knf.dev.librarymanagementsystem.controller;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knf.dev.librarymanagementsystem.dto.BookDTO;
import com.knf.dev.librarymanagementsystem.entity.Book;
import com.knf.dev.librarymanagementsystem.service.AuthorService;
import com.knf.dev.librarymanagementsystem.service.BookService;
import com.knf.dev.librarymanagementsystem.service.CategoryService;
import com.knf.dev.librarymanagementsystem.service.PublisherService;

@Controller
public class BookController {

	private static final int DEFAULT_PAGE_SIZE = 5;

	private final BookService bookService;
	private final AuthorService authorService;
	private final CategoryService categoryService;
	private final PublisherService publisherService;

	public BookController(PublisherService publisherService, CategoryService categoryService, BookService bookService,
			AuthorService authorService) {
		this.authorService = authorService;
		this.bookService = bookService;
		this.categoryService = categoryService;
		this.publisherService = publisherService;
	}

	@RequestMapping({ "/books", "/" })
	public String findAllBooks(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		var currentPage = page.orElse(1);
		var pageSize = size.orElse(DEFAULT_PAGE_SIZE);

		var bookPage = bookService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("books", bookPage);

		var totalPages = bookPage.getTotalPages();
		if (totalPages > 0) {
			var pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "list-books";
	}

	@RequestMapping("/searchBook")
	public String searchBook(@Param("keyword") String keyword, Model model) {

		model.addAttribute("books", bookService.searchBooks(keyword));
		model.addAttribute("keyword", keyword);
		return "list-books";
	}

	@RequestMapping("/book/{id}")
	public String findBookById(@PathVariable("id") Long id, Model model) {

		model.addAttribute("book", bookService.findBookById(id));
		return "list-book";
	}

	@GetMapping("/add")
	public String showCreateForm(Model model) {

		model.addAttribute("bookDTO", new BookDTO());
		model.addAttribute("categories", categoryService.findAllCategories());
		model.addAttribute("authors", authorService.findAllAuthors());
		model.addAttribute("publishers", publisherService.findAllPublishers());
		return "add-book";
	}

	@PostMapping("/add-book")
	public String createBook(@Valid BookDTO bookDTO, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("categories", categoryService.findAllCategories());
			model.addAttribute("authors", authorService.findAllAuthors());
			model.addAttribute("publishers", publisherService.findAllPublishers());
			return "add-book";
		}

		var book = new Book(bookDTO.getIsbn(), bookDTO.getName(), bookDTO.getSerialName(),
				bookDTO.getDescription());

		if (bookDTO.getAuthorId() != null) {
			var author = authorService.findAuthorById(bookDTO.getAuthorId());
			book.addAuthors(author);
		}
		if (bookDTO.getCategoryId() != null) {
			var category = categoryService.findCategoryById(bookDTO.getCategoryId());
			book.addCategories(category);
		}
		if (bookDTO.getPublisherId() != null) {
			var publisher = publisherService.findPublisherById(bookDTO.getPublisherId());
			book.addPublishers(publisher);
		}

		bookService.createBook(book);
		return "redirect:/books";
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {

		var book = bookService.findBookByIdWithRelationships(id);

		var bookDTO = new BookDTO();
		bookDTO.setId(book.getId());
		bookDTO.setIsbn(book.getIsbn());
		bookDTO.setName(book.getName());
		bookDTO.setSerialName(book.getSerialName());
		bookDTO.setDescription(book.getDescription());

		if (!book.getAuthors().isEmpty()) {
			bookDTO.setAuthorId(book.getAuthors().iterator().next().getId());
		}
		if (!book.getCategories().isEmpty()) {
			bookDTO.setCategoryId(book.getCategories().iterator().next().getId());
		}
		if (!book.getPublishers().isEmpty()) {
			bookDTO.setPublisherId(book.getPublishers().iterator().next().getId());
		}

		model.addAttribute("bookDTO", bookDTO);
		model.addAttribute("categories", categoryService.findAllCategories());
		model.addAttribute("authors", authorService.findAllAuthors());
		model.addAttribute("publishers", publisherService.findAllPublishers());
		return "update-book";
	}

	@PostMapping("/update-book/{id}")
	public String updateBook(@PathVariable("id") Long id, @Valid BookDTO bookDTO, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("categories", categoryService.findAllCategories());
			model.addAttribute("authors", authorService.findAllAuthors());
			model.addAttribute("publishers", publisherService.findAllPublishers());
			return "update-book";
		}

		var existingBook = bookService.findBookByIdWithRelationships(id);
		existingBook.setIsbn(bookDTO.getIsbn());
		existingBook.setName(bookDTO.getName());
		existingBook.setSerialName(bookDTO.getSerialName());
		existingBook.setDescription(bookDTO.getDescription());

		existingBook.getAuthors().clear();
		existingBook.getCategories().clear();
		existingBook.getPublishers().clear();

		if (bookDTO.getAuthorId() != null) {
			var author = authorService.findAuthorById(bookDTO.getAuthorId());
			existingBook.addAuthors(author);
		}
		if (bookDTO.getCategoryId() != null) {
			var category = categoryService.findCategoryById(bookDTO.getCategoryId());
			existingBook.addCategories(category);
		}
		if (bookDTO.getPublisherId() != null) {
			var publisher = publisherService.findPublisherById(bookDTO.getPublisherId());
			existingBook.addPublishers(publisher);
		}

		bookService.updateBook(existingBook);
		return "redirect:/books";
	}

	@RequestMapping("/remove-book/{id}")
	public String deleteBook(@PathVariable("id") Long id) {
		bookService.deleteBook(id);
		return "redirect:/books";
	}
}
