package com.knf.dev.librarymanagementsystem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.knf.dev.librarymanagementsystem.entity.Category;

public interface CategoryService {

	List<Category> findAllCategories();

	Category findCategoryById(Long id);

	void createCategory(Category category);

	void updateCategory(Category category);

	void deleteCategory(Long id);

	Page<Category> findPaginated(Pageable pageable);

	long countAll();
}
