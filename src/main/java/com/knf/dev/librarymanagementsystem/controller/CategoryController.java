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

import com.knf.dev.librarymanagementsystem.dto.CategoryDTO;
import com.knf.dev.librarymanagementsystem.entity.Category;
import com.knf.dev.librarymanagementsystem.service.CategoryService;

@Controller
public class CategoryController {

	private static final int DEFAULT_PAGE_SIZE = 5;

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@RequestMapping("/categories")
	public String findAllCategories(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		var currentPage = page.orElse(1);
		var pageSize = size.orElse(DEFAULT_PAGE_SIZE);
		var categoryPage = categoryService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("categories", categoryPage);

		int totalPages = categoryPage.getTotalPages();
		if (totalPages > 0) {
			var pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "list-categories";
	}

	@RequestMapping("/category/{id}")
	public String findCategoryById(@PathVariable("id") Long id, Model model) {

		model.addAttribute("category", categoryService.findCategoryById(id));
		return "list-category";
	}

	@GetMapping("/addCategory")
	public String showCreateForm(Model model) {
		model.addAttribute("categoryDTO", new CategoryDTO());
		return "add-category";
	}

	@PostMapping("/add-category")
	public String createCategory(@Valid CategoryDTO categoryDTO, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-category";
		}

		var category = new Category(categoryDTO.getName());
		categoryService.createCategory(category);
		return "redirect:/categories";
	}

	@GetMapping("/updateCategory/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {

		var category = categoryService.findCategoryById(id);
		var categoryDTO = new CategoryDTO(category.getId(), category.getName());
		model.addAttribute("categoryDTO", categoryDTO);
		return "update-category";
	}

	@PostMapping("/update-category/{id}")
	public String updateCategory(@PathVariable("id") Long id, @Valid CategoryDTO categoryDTO, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "update-category";
		}

		var existingCategory = categoryService.findCategoryById(id);
		existingCategory.setName(categoryDTO.getName());
		categoryService.updateCategory(existingCategory);
		return "redirect:/categories";
	}

	@RequestMapping("/remove-category/{id}")
	public String deleteCategory(@PathVariable("id") Long id) {
		categoryService.deleteCategory(id);
		return "redirect:/categories";
	}
}
