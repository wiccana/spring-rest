//package com.restful.app.like;
//
//import java.net.URI;
//import java.util.List;
//import java.util.Optional;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import com.restful.app.exception.CustomException;
//
//@RestController
//public class LikeController {
//	
//	@Autowired
//	private LikeRepository likeRepository;	
//	
//	@GetMapping("/likes")
//	public List<Like> getAllLikes() {
//		return likeRepository.findAll();
//	}
//	
//	@GetMapping("/likes/{id}")
//	public Optional<Like> getLike(@PathVariable Long id) {
//		Optional<Like> like = likeRepository.findById(id);
//		if (!like.isPresent()) {
//			throw new CustomException("id-" + id, HttpStatus.NOT_FOUND);
//		}
//		return likeRepository.findById(id);
//	}
//	
//	@PostMapping("likes")
//	public ResponseEntity<Object> createLike(@Valid @RequestBody Like like) {
//		Like newLike = likeRepository.save(like);
//		if (newLike==null) {
//			throw new CustomException("creation error", HttpStatus.INTERNAL_SERVER_ERROR); //TODO: chequear si conviene usar otro status code 
//		}
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//		.path("/{id}")
//		.buildAndExpand(newLike.getId())
//		.toUri();
//		return ResponseEntity.created(location).build();
//	}
//	
//	@PutMapping("likes/{id}")
//	public ResponseEntity<Object> updateLike(@Valid @RequestBody Like like) {
//		Like updatedLike = likeRepository.save(like);
//		if (updatedLike==null) {
//			throw new CustomException("id-" + like.getId(), HttpStatus.NOT_FOUND);
//		}
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//		.path("/{id}")
//		.buildAndExpand(updatedLike.getId())
//		.toUri();
//		return ResponseEntity.created(location).build();
//	}
//	
//	
//	@DeleteMapping("likes/{id}")
//	public void deleteLike(@PathVariable Long id) {
//		likeRepository.deleteById(id);
//	}
//
//}
