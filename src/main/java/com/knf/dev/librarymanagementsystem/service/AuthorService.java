package com.knf.dev.librarymanagementsystem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.knf.dev.librarymanagementsystem.entity.Author;

public interface AuthorService {

	List<Author> findAllAuthors();

	Author findAuthorById(Long id);

	void createAuthor(Author author);

	void updateAuthor(Author author);

	void deleteAuthor(Long id);

	Page<Author> findPaginated(Pageable pageable);

	long countAll();
}
