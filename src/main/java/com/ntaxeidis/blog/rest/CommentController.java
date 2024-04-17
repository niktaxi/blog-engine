package com.ntaxeidis.blog.rest;

import com.ntaxeidis.blog.model.dto.CommentDto;
import com.ntaxeidis.blog.model.dto.NewCommentDto;
import com.ntaxeidis.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	@GetMapping(value = "/{id}/comments")
	@ResponseStatus(HttpStatus.OK)
	public List<CommentDto> getComments(@PathVariable Long id) {
		return commentService.getCommentsForPost(id);
	}

	@PostMapping(value = "/{id}/comment")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveComment(@PathVariable Long id, NewCommentDto newComment) {
		commentService.addComment(newComment);
	}

	@DeleteMapping(value = "/comment/{id}")
	@ResponseStatus(HttpStatus.RESET_CONTENT)
	public void deleteComment(@PathVariable Long id) {
		commentService.deleteComment(id);
	}
}
