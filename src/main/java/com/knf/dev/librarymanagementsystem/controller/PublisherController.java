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

import com.knf.dev.librarymanagementsystem.dto.PublisherDTO;
import com.knf.dev.librarymanagementsystem.entity.Publisher;
import com.knf.dev.librarymanagementsystem.service.PublisherService;

@Controller
public class PublisherController {

	private static final int DEFAULT_PAGE_SIZE = 5;

	private final PublisherService publisherService;

	public PublisherController(PublisherService publisherService) {
		this.publisherService = publisherService;
	}

	@RequestMapping("/publishers")
	public String findAllPublishers(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		var currentPage = page.orElse(1);
		var pageSize = size.orElse(DEFAULT_PAGE_SIZE);
		var publisherPage = publisherService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("publishers", publisherPage);

		int totalPages = publisherPage.getTotalPages();
		if (totalPages > 0) {
			var pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "list-publishers";
	}

	@RequestMapping("/publisher/{id}")
	public String findPublisherById(@PathVariable("id") Long id, Model model) {

		model.addAttribute("publisher", publisherService.findPublisherById(id));
		return "list-publisher";
	}

	@GetMapping("/addPublisher")
	public String showCreateForm(Model model) {
		model.addAttribute("publisherDTO", new PublisherDTO());
		return "add-publisher";
	}

	@PostMapping("/add-publisher")
	public String createPublisher(@Valid PublisherDTO publisherDTO, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-publisher";
		}

		var publisher = new Publisher(publisherDTO.getName());
		publisherService.createPublisher(publisher);
		return "redirect:/publishers";
	}

	@GetMapping("/updatePublisher/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {

		var publisher = publisherService.findPublisherById(id);
		var publisherDTO = new PublisherDTO(publisher.getId(), publisher.getName());
		model.addAttribute("publisherDTO", publisherDTO);
		return "update-publisher";
	}

	@PostMapping("/update-publisher/{id}")
	public String updatePublisher(@PathVariable("id") Long id, @Valid PublisherDTO publisherDTO,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "update-publisher";
		}

		var existingPublisher = publisherService.findPublisherById(id);
		existingPublisher.setName(publisherDTO.getName());
		publisherService.updatePublisher(existingPublisher);
		return "redirect:/publishers";
	}

	@RequestMapping("/remove-publisher/{id}")
	public String deletePublisher(@PathVariable("id") Long id) {
		publisherService.deletePublisher(id);
		return "redirect:/publishers";
	}
}
