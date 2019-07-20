package com.restful.app.like;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.restful.app.book.Book;
import com.restful.app.user.User;

@Entity
public class BookLike {

	@Id
	private Long id;
//	@ManyToOne
//	private User user;
	@ManyToOne
	private Book book;
	
	protected BookLike() {
		
	}
	
	public BookLike(Book book) {
		this.book = book;
//		this.user = user;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
}
