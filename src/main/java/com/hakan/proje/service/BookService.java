package com.hakan.proje.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hakan.proje.dal.BookRepository;
import com.hakan.proje.domain.Book;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(allEntries = true, cacheNames = { "book_list", "books" })
	public Book saveBook(Book book) {
		return bookRepository.save(book);

	}

	// bookların hepsini getirir
	@Cacheable(value = "book_list")
	public List<Book> getBookList() {
		return bookRepository.findAll();
	}

	// İd ile book'u bulup getirir.
	@CachePut(value = "books", key = "#bookId")
	public Book selectedBookbyId(Long bookId) {
		return bookRepository.getBookById(bookId);
	}

	// book id si ile book siler
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(key = "#bookId", allEntries = true, cacheNames = { "book_list", "books" })
	public Boolean deleteBookbyId(Long bookId) {
		bookRepository.deleteById(bookId);
		return true;
	}

	public BookRepository getBookRepository() {
		return bookRepository;
	}

	public void setBookRepository(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

}
