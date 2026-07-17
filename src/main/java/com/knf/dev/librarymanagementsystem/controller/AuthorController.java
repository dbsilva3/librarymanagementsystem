package com.knf.dev.librarymanagementsystem.controller;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knf.dev.librarymanagementsystem.dto.AuthorDTO;
import com.knf.dev.librarymanagementsystem.entity.Author;
import com.knf.dev.librarymanagementsystem.service.AuthorService;

@Controller
public class AuthorController {

	private static final int DEFAULT_PAGE_SIZE = 5;

	private final AuthorService authorService;

	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@RequestMapping("/authors")
	public String findAllAuthors(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		var currentPage = page.orElse(1);
		var pageSize = size.orElse(DEFAULT_PAGE_SIZE);
		var authorPage = authorService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("authors", authorPage);

		int totalPages = authorPage.getTotalPages();
		if (totalPages > 0) {
			var pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "list-authors";
	}

	@RequestMapping("/author/{id}")
	public String findAuthorById(@PathVariable("id") Long id, Model model) {

		model.addAttribute("author", authorService.findAuthorById(id));
		return "list-author";
	}

	@GetMapping("/addAuthor")
	public String showCreateForm(Model model) {
		model.addAttribute("authorDTO", new AuthorDTO());
		return "add-author";
	}

	@PostMapping("/add-author")
	public String createAuthor(@Valid AuthorDTO authorDTO, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-author";
		}

		var author = new Author(authorDTO.getName(), authorDTO.getDescription());
		authorService.createAuthor(author);
		return "redirect:/authors";
	}

	@GetMapping("/updateAuthor/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {

		var author = authorService.findAuthorById(id);
		var authorDTO = new AuthorDTO(author.getId(), author.getName(), author.getDescription());
		model.addAttribute("authorDTO", authorDTO);
		return "update-author";
	}

	@PostMapping("/update-author/{id}")
	public String updateAuthor(@PathVariable("id") Long id, @Valid AuthorDTO authorDTO, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "update-author";
		}

		var existingAuthor = authorService.findAuthorById(id);
		existingAuthor.setName(authorDTO.getName());
		existingAuthor.setDescription(authorDTO.getDescription());
		authorService.updateAuthor(existingAuthor);
		return "redirect:/authors";
	}

	@RequestMapping("/remove-author/{id}")
	public String deleteAuthor(@PathVariable("id") Long id) {
		authorService.deleteAuthor(id);
		return "redirect:/authors";
	}
}
