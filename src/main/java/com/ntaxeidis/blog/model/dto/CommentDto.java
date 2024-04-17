package com.ntaxeidis.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentDto {

	private Long id;

	private String comment;

	private String author;

	private LocalDateTime creationDate;
}
