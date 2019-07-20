package com.restful.app.like;

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

import com.restful.app.book.Book;
import com.restful.app.book.BookRepository;
import com.restful.app.exception.CustomException;
import com.restful.app.user.User;
import com.restful.app.user.UserRepository;

@RestController
public class BookLikeController {
	
	@Autowired
	private BookLikeRepository bookLikeRepository;	
	
	@Autowired
	private BookRepository bookRepository;	
	
	@Autowired
	private UserRepository userRepository;	
	
	@GetMapping("/likes")
	public List<BookLike> getAllBookLikes() {
		return bookLikeRepository.findAll();
	}
	
//	@GetMapping("/likes/{id}")
//	public Optional<BookLike> getBookLike(@PathVariable Long id) {
//		Optional<BookLike> like = likeRepository.findById(id);
//		if (!like.isPresent()) {
//			throw new CustomException("id-" + id, HttpStatus.NOT_FOUND);
//		}
//		return likeRepository.findById(id);
//	}
	
	@PostMapping("books/{id}/likes")
	public ResponseEntity<Object> createBookLike(@PathVariable Long id) {
		Optional<Book> book = bookRepository.findById(id);
		Optional<User> user = userRepository.findById((long) 1); //TODO complete with current user
		if (!book.isPresent() || !user.isPresent()) {
			throw new CustomException("Book id-" + id, HttpStatus.NOT_FOUND);
		}
		BookLike newBookLike = new BookLike(book.get());
		if (newBookLike==null) {
			throw new CustomException("creation error", HttpStatus.INTERNAL_SERVER_ERROR); //TODO: chequear si conviene usar otro status code 
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(newBookLike.getId())
		.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("likes/{id}")
	public ResponseEntity<Object> updateBookLike(@Valid @RequestBody BookLike like) {
		BookLike updatedBookLike = bookLikeRepository.save(like);
		if (updatedBookLike==null) {
			throw new CustomException("id-" + like.getId(), HttpStatus.NOT_FOUND);
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(updatedBookLike.getId())
		.toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@DeleteMapping("likes/{id}")
	public void deleteBookLike(@PathVariable Long id) {
		bookLikeRepository.deleteById(id);
	}

}
