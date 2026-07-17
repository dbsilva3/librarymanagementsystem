package com.knf.dev.librarymanagementsystem.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PublisherDTO {

	private Long id;

	@NotBlank(message = "Publisher name is required")
	@Size(max = 100, message = "Publisher name must not exceed 100 characters")
	private String name;

	public PublisherDTO() {
	}

	public PublisherDTO(Long id, String name) {
		this.id = id;
		this.name = name;
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
}
