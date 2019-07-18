package com.restful.app.book;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restful.app.exception.CustomException;

@RestController
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;	
	
	@GetMapping("/books")
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	@GetMapping("/books/{id}")
	public Optional<Book> getBook(@PathVariable Long id) {
		Optional<Book> book = bookRepository.findById(id);
		if (!book.isPresent()) {
			throw new CustomException("id-" + id, HttpStatus.NOT_FOUND);
		}
		return bookRepository.findById(id);
	}
	
	@PostMapping("books")
	public ResponseEntity<Object> createBook(@Valid @RequestBody Book book) {
		Book newBook = bookRepository.save(book);
		if (newBook==null) {
			throw new CustomException("creation error", HttpStatus.INTERNAL_SERVER_ERROR); //TODO: chequear si conviene usar otro status code 
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(newBook.getId())
		.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("books/{id}")
	public ResponseEntity<Object> updateBook(@Valid @RequestBody Book book) {
		Book updatedBook = bookRepository.save(book);
		if (updatedBook==null) {
			throw new CustomException("id-" + book.getId(), HttpStatus.NOT_FOUND);
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(updatedBook.getId())
		.toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@DeleteMapping("books/{id}")
	public void deleteBook(@PathVariable Long id) {
		bookRepository.deleteById(id);
	}

}
