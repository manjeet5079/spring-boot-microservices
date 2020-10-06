package com.hcl.traning.microservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.traning.microservices.bean.Book;

/**
 * @author Manjeet Kumar
 *
 * Jul 2, 2020
 */

@RestController
public class BookController {
	
	@GetMapping("/books")
	public Book retrieveBooks() {
		
		return new Book(11,"Spring Boot Book","Manjeet Kumar");
	}

}
