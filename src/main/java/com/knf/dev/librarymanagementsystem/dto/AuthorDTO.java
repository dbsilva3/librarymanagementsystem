package com.knf.dev.librarymanagementsystem.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorDTO {

	private Long id;

	@NotBlank(message = "Author name is required")
	@Size(max = 100, message = "Author name must not exceed 100 characters")
	private String name;

	@NotBlank(message = "Description is required")
	@Size(max = 250, message = "Description must not exceed 250 characters")
	private String description;

	public AuthorDTO() {
	}

	public AuthorDTO(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
