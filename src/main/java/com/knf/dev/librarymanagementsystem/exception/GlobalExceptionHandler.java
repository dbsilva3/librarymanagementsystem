package com.knf.dev.librarymanagementsystem.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFoundException(NotFoundException ex) {
		var modelAndView = new ModelAndView("error/404");
		modelAndView.addObject("message", ex.getMessage());
		modelAndView.setStatus(org.springframework.http.HttpStatus.NOT_FOUND);
		return modelAndView;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleGenericException(Exception ex) {
		var modelAndView = new ModelAndView("error/404");
		modelAndView.addObject("message", "An unexpected error occurred. Please try again later.");
		modelAndView.addObject("detail", ex.getMessage());
		modelAndView.setStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
		return modelAndView;
	}
}
