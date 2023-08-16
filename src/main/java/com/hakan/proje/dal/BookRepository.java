package com.hakan.proje.dal;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.hakan.proje.domain.Author;
import com.hakan.proje.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	
	@Query("SELECT b FROM book b WHERE b.bookId = ?1")
	public Book getBookById(Long bookId);
	
	@Query("SELECT b FROM book b WHERE b.bookName = :bookName")
	public Book getBookByBookName(@Param("bookName") String bookName);
	
	@Query("SELECT b FROM book b WHERE b.bookName IN :bookName")
	public List<Author> findBooksByBookName(@Param("bookName") String bookName);
	
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	@Modifying
	@Query("UPDATE book b SET b.bookName = :bookName WHERE b.bookId = :bookIdPar")
	public Integer updateAuthorName(@Param("bookName") String bookName, @Param("bookIdPar") Long bookId);
	
	@Query("select b FROM book b WHERE b.publishDate = :date")
	public List<Book> getBookByDate(@Param("date") LocalDate date);
	
	
}
