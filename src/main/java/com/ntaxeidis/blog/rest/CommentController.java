package com.ntaxeidis.blog.rest;

import com.ntaxeidis.blog.model.dto.CommentDto;
import com.ntaxeidis.blog.model.dto.NewCommentDto;
import com.ntaxeidis.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CommentDto getComment(@PathVariable Long id) {
		return commentService.getComment(id);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.RESET_CONTENT)
	public void deleteComment(@PathVariable Long id) {
		commentService.deleteComment(id);
	}

	@PostMapping(value = "/{id}/like")
	@ResponseStatus(HttpStatus.OK)
	public Long likeComment(@PathVariable Long id) {
		return commentService.likeComment(id);
	}

	@PatchMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void editComment(@PathVariable Long id, @RequestBody NewCommentDto newCommentDto) {
		commentService.editComment(id, newCommentDto);
	}

	@PostMapping(value = "/{id}/reply")
	@ResponseStatus(HttpStatus.OK)
	public Long replyToComment(@PathVariable Long id, @RequestBody NewCommentDto newCommentDto) {
		return commentService.replyToComment(id, newCommentDto);
	}
}
