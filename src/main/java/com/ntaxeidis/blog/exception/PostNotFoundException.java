package com.ntaxeidis.blog.exception;

public class PostNotFoundException extends RuntimeException {
	public PostNotFoundException(Long id) {
		super(String.format("Post with id %s was not found", id));
	}
}
