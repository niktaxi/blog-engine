package com.ntaxeidis.blog.exception;

public class CommentNotFoundException extends RuntimeException {
	public CommentNotFoundException(Long id) {
		super(String.format("Comment with id %s was not found", id));
	}
}
