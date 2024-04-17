package com.ntaxeidis.blog.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Data
@NoArgsConstructor
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

	public Comment(String comment, String author) {
		this.comment = comment;
		this.author = author;
	}
}
