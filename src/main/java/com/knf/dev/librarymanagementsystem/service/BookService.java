package com.knf.dev.librarymanagementsystem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.knf.dev.librarymanagementsystem.entity.Book;

public interface BookService {

	List<Book> findAllBooks();

	List<Book> searchBooks(String keyword);

	Book findBookById(Long id);

	Book findBookByIdWithRelationships(Long id);

	void createBook(Book book);

	void updateBook(Book book);

	void deleteBook(Long id);

	Page<Book> findPaginated(Pageable pageable);

	long countAll();
}
