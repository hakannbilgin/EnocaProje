package com.hakan.proje.dal;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.hakan.proje.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

	@Query("SELECT a FROM author a WHERE a.authorId = ?1")
	public Author getAuthorById(Long authorId);
	
	@Query("SELECT a FROM author a WHERE a.fullName = :authorNameParameter")
	public Author findAuthorByName(@Param("authorNameParameter") String authorName);
	
	@Query("SELECT a FROM author a WHERE a.fullName IN :fullName")
	public List<Author> findAuthorsByFullName(@Param("fullName") String fullName);
	
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	@Modifying
	@Query("UPDATE author a SET a.fullName = :authorName WHERE a.authorId = :authoridPar")
	public Integer updateAuthorName(@Param("authorName") String authorName, @Param("authoridPar") Long authorId);
	
	
	
}
