package com.knf.dev.librarymanagementsystem.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BookDTO {

	private Long id;

	@NotBlank(message = "ISBN is required")
	@Size(max = 50, message = "ISBN must not exceed 50 characters")
	private String isbn;

	@NotBlank(message = "Book name is required")
	@Size(max = 100, message = "Book name must not exceed 100 characters")
	private String name;

	@NotBlank(message = "Serial name is required")
	@Size(max = 50, message = "Serial name must not exceed 50 characters")
	private String serialName;

	@NotBlank(message = "Description is required")
	@Size(max = 250, message = "Description must not exceed 250 characters")
	private String description;

	private Long authorId;
	private Long categoryId;
	private Long publisherId;

	public BookDTO() {
	}

	public BookDTO(Long id, String isbn, String name, String serialName, String description, Long authorId,
			Long categoryId, Long publisherId) {
		this.id = id;
		this.isbn = isbn;
		this.name = name;
		this.serialName = serialName;
		this.description = description;
		this.authorId = authorId;
		this.categoryId = categoryId;
		this.publisherId = publisherId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerialName() {
		return serialName;
	}

	public void setSerialName(String serialName) {
		this.serialName = serialName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}
}
