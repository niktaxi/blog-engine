package com.ntaxeidis.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PostDto {

	private String title;

	private String content;

	private LocalDateTime creationDate;

	private	LocalDateTime modifiedDate;

	private List<CommentDto> comments;

	private Long likes;
}
