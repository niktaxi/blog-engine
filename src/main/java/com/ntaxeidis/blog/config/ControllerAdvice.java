package com.ntaxeidis.blog.config;

import com.ntaxeidis.blog.exception.CommentNotFoundException;
import com.ntaxeidis.blog.exception.PostNotFoundException;
import com.ntaxeidis.blog.model.dto.ErrorMessage;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

public class ControllerAdvice {
	@ExceptionHandler(value = {PostNotFoundException.class, CommentNotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage handleTokenRefreshException(PostNotFoundException ex, WebRequest request) {
		return new ErrorMessage(
			HttpStatus.FORBIDDEN.value(),
			new Date(),
			ex.getMessage(),
			request.getDescription(false));
	}
}
