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


import com.hakan.proje.domain.Author;
import com.hakan.proje.service.AuthorService;

@RestController
@RequestMapping("/rest/author")
public class AuthorRestController {

	@Autowired
	private AuthorService authorService;

	@PostMapping("/saveAuthor")
	public Author saveAuthor(@RequestBody Author author) {

		return authorService.saveAuthor(author);
	}

	// authorları Listeler
	@GetMapping("/getAuthors")
	public List<Author> getAuthorList() {

		return authorService.getAuthorsList();
	}

	// seçilen authoru getirir
	@GetMapping("/getAuthorById/{authorId}")
	public Author getAuthorById(@PathVariable(name = "authorId", required = true) Long authorId) {

		return authorService.selectedAuthor(authorId);

	}
	
	@PutMapping("/updateAuthorName/{authorId}")
	public Author updateAuthorName(@RequestBody Author author,
			@PathVariable(name = "authorId", required = true) Long authorId) {

		Author savedAuthor = authorService.selectedAuthor(authorId);

		savedAuthor.setFullName(author.getFullName());

		return authorService.saveAuthor(savedAuthor);
	}
	
	@DeleteMapping("/deleteAuthorById/{authorId}")
	public Boolean deleteAuthorById(@PathVariable(required = true) Long authorId) {

		return authorService.deleteAuthorByID(authorId) ? true : false;

	}
	
	
	
}
