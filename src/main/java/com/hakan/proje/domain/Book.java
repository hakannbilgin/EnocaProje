package com.hakan.proje.domain;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "book")
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bookid")
	private long bookId;
	
	@Column(length = 200, nullable = false, name = "book_name")
	private String bookName;

	@Column(nullable = false, name = "publishDate")
	private LocalDate publishDate;

	@ManyToOne
	@JoinColumn(name = "authorid", nullable = false)
	private Author author;
	
	public Book(long bookId, String bookName, LocalDate publishDate, Author author) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.publishDate = publishDate;
		this.author = author;
	}

	public Book() {

	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, bookId, bookName, publishDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && bookId == other.bookId
				&& Objects.equals(bookName, other.bookName) && Objects.equals(publishDate, other.publishDate);
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", publishDate=" + publishDate + ", author="
				+ author + "]";
	}

}
