package com.ntaxeidis.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

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

	@CreationTimestamp
	private LocalDateTime creationDate;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "post")
	private List<Comment> comments;

	public Post(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
