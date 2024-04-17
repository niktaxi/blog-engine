package com.ntaxeidis.blog.rest;

import com.ntaxeidis.blog.model.dto.CommentDto;
import com.ntaxeidis.blog.model.dto.NewCommentDto;
import com.ntaxeidis.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.RESET_CONTENT)
	public void deleteComment(@PathVariable Long id) {
		commentService.deleteComment(id);
	}
}
