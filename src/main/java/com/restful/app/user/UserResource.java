package com.restful.app.user;

import java.net.URI;
import java.util.List;

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
import com.restful.app.exception.CustomExceptionResponse;

@RestController
public class UserResource {	
	
	@Autowired
	private UserDaoService userService;	
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable Integer id) {
		User user = userService.findUser(id);
		if (user==null) {
			throw new CustomException("id-" + id, HttpStatus.NOT_FOUND);
		}
		return userService.findUser(id);
	}
	
	@PostMapping("users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User newUser = userService.save(user);
		if (newUser==null) {
			throw new CustomException("creation error", HttpStatus.INTERNAL_SERVER_ERROR); //TODO: chequear si conviene usar otro status code 
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(newUser.getId())
		.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		User user = userService.deleteUser(id);
		if(user==null) {
			throw new CustomException("id-" + id, HttpStatus.NOT_FOUND);
		}
	}

}
