package com.restful.app.book;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.restful.app.author.Author;

@Entity
public class Book{
	
	@Id
	@GeneratedValue
	private Long id;
	@Size(min=3, message="Title should be at least 3 characters long")
	private String title;
	@ManyToOne
	private Author author;
	private Integer likes;
	private Integer comments;
	private Date firstPublicationDate;
	private Language language;
		
	protected Book() {
		
	}
	
	private enum Language {
		spanish,
		english,
		french,
		german
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getComments() {
		return comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	public Date getFirstPublicationDate() {
		return firstPublicationDate;
	}

	public void setFirstPublicationDate(Date firstPublicationDate) {
		this.firstPublicationDate = firstPublicationDate;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}


}
