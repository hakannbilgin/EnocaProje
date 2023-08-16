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


import com.hakan.proje.dal.AuthorRepository;
import com.hakan.proje.domain.Author;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(allEntries = true, cacheNames = { "authors_list", "authors" })
	public Author saveAuthor(Author author) {
		return authorRepository.save(author);

	}

	@Cacheable(value = "author_list")
	public List<Author> getAuthorsList() {
		return authorRepository.findAll();
	}

	// İd ile author bulup getirir
	@CachePut(value = "authors", key = "#authorId")
	public Author selectedAuthor(Long authorId) {
		return authorRepository.getAuthorById(authorId);
	}

	// seçili authoru id si ile siler
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(key = "#authorId", allEntries = true, cacheNames = { "author_list", "authors" })
	public Boolean deleteAuthorByID(Long authorId) {
		authorRepository.deleteById(authorId);
		return true;
	}

	
	
	
	

	public AuthorRepository getAuthorRepository() {
		return authorRepository;
	}

	public void setAuthorRepository(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}
}
