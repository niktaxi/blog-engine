package com.ntaxeidis.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewPostDto {
	private String title;
	private String content;
}
