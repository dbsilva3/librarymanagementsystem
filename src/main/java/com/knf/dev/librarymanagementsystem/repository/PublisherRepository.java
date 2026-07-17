package com.knf.dev.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.knf.dev.librarymanagementsystem.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

	@Query("SELECT COUNT(p) FROM Publisher p")
	long countAll();
}
