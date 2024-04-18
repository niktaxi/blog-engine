package com.ntaxeidis.blog.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

@Entity
@Data
@NoArgsConstructor
public class Comment {
	@Id
	@GeneratedValue
	private Long id;

	private String content;

	private String author;

	@ColumnDefault("0")
	@Generated(GenerationTime.INSERT)
	private Long likes;

	@CreationTimestamp
	private LocalDateTime creationDate;

	@UpdateTimestamp
	private	LocalDateTime modifiedDate;

	@ManyToOne
	private Post post;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Comment> thread;

	public Comment(String content, String author) {
		this.content = content;
		this.author = author;
	}
}
