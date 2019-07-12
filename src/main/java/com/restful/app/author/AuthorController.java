package com.restful.app.author;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restful.app.exception.CustomException;

@RestController
public class AuthorController {
	
	@Autowired
	private AuthorRepository authorRepository;	
	
	@GetMapping("/authors")
	public List<Author> getAllAuthors() {
		return authorRepository.findAll();
	}
	
	@GetMapping("/authors/{id}")
	public Optional<Author> getAuthor(@PathVariable Long id) {
		Optional<Author> author = authorRepository.findById(id);
		if (!author.isPresent()) {
			throw new CustomException("id-" + id, HttpStatus.NOT_FOUND);
		}
		return authorRepository.findById(id);
	}
	
	@PostMapping("authors")
	public ResponseEntity<Object> createAuthor(@Valid @RequestBody Author author) {
		Author newAuthor = authorRepository.save(author);
		if (newAuthor==null) {
			throw new CustomException("creation error", HttpStatus.INTERNAL_SERVER_ERROR); //TODO: chequear si conviene usar otro status code 
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(newAuthor.getId())
		.toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@DeleteMapping("authors/{id}")
	public void deleteAuthor(@PathVariable Long id) {
		authorRepository.deleteById(id);
	}

}
