package com.ntaxeidis.blog.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.springframework.data.annotation.CreatedDate;

@Entity
public class Comment {
	@Id
	@GeneratedValue
	private Long id;

	private String comment;

	private String author;

	@CreatedDate
	private LocalDateTime creationDate;

	@ManyToOne
	private Post post;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Comment(String comment, String author) {
		this.comment = comment;
		this.author = author;
	}

	public Comment() {
	}

	public Post getPost() {
			return post;
		}

	public void setPost(Post post) {
		this.post = post;
	}
}
