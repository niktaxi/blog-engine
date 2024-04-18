package com.ntaxeidis.blog.model.dto;

import com.ntaxeidis.blog.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CommentDto {

	private Long id;

	private String content;

	private String author;

	private Long likes;

	private LocalDateTime creationDate;

	private	LocalDateTime modifiedDate;

	private List<Comment> thread;
}
