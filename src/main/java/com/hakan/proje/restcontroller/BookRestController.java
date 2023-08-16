package com.hakan.proje.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.hakan.proje.domain.Book;
import com.hakan.proje.service.BookService;

@RestController
@RequestMapping("/rest/book")
public class BookRestController {

	@Autowired
	private BookService bookService;
	
	
	@PostMapping("/saveBook")
	public Book saveAuthor(@RequestBody Book book) {

		return bookService.saveBook(book);
	}
	
	
	
}
