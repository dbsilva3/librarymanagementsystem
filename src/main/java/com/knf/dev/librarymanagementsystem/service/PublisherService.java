package com.knf.dev.librarymanagementsystem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.knf.dev.librarymanagementsystem.entity.Publisher;

public interface PublisherService {

	List<Publisher> findAllPublishers();

	Publisher findPublisherById(Long id);

	void createPublisher(Publisher publisher);

	void updatePublisher(Publisher publisher);

	void deletePublisher(Long id);

	Page<Publisher> findPaginated(Pageable pageable);

	long countAll();
}
