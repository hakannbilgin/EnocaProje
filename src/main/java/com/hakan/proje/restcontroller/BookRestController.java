package com.hakan.proje.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	// bookları Listeler
		@GetMapping("/getBooks")
		public List<Book> getBooksList() {

			return bookService.getBookList();
		}
		// seçilen book'u getirir
		@GetMapping("/getBookById/{bookId}")
		public Book getBookById(@PathVariable(name = "bookId", required = true) Long bookId) {

			return bookService.selectedBookbyId(bookId);

		}
		
		@PutMapping("/updateBookName/{bookId}")
		public Book updateBookName(@RequestBody Book book,
				@PathVariable(name = "bookId", required = true) Long bookId) {

			Book savedBook = bookService.selectedBookbyId(bookId);

			savedBook.setBookName(book.getBookName());

			return bookService.saveBook(savedBook);
		}
		
		@DeleteMapping("/deleteBookById/{bookId}")
		public Boolean deleteBookById(@PathVariable(required = true) Long bookId) {

			return bookService.deleteBookbyId(bookId) ? true : false;

		}
		
}
