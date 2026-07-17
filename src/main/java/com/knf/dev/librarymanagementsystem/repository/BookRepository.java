package com.knf.dev.librarymanagementsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.knf.dev.librarymanagementsystem.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("SELECT b FROM Book b WHERE b.name LIKE %?1% OR b.isbn LIKE %?1% OR b.serialName LIKE %?1%")
	List<Book> search(String keyword);

	@Query("SELECT COUNT(b) FROM Book b")
	long countAll();

	@Query("SELECT b FROM Book b LEFT JOIN FETCH b.authors LEFT JOIN FETCH b.categories LEFT JOIN FETCH b.publishers WHERE b.id = :id")
	Optional<Book> findByIdWithRelationships(@Param("id") Long id);
}
