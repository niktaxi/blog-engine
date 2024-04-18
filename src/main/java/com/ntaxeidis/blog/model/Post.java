package com.ntaxeidis.blog.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import javax.persistence.OneToMany;

@Entity
@Data
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue
	private Long id;

	private String title;

	@Column(length = 4096)
	private String content;

	@ColumnDefault("0")
	@Generated(GenerationTime.INSERT)
	private Long likes;

	@CreationTimestamp
	private LocalDateTime creationDate;

	@UpdateTimestamp
	private	LocalDateTime modifiedDate;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "post")
	private List<Comment> comments;

	public Post(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
