package com.ntaxeidis.blog.rest;

import com.ntaxeidis.blog.model.dto.CommentDto;
import com.ntaxeidis.blog.model.dto.NewCommentDto;
import com.ntaxeidis.blog.model.dto.NewPostDto;
import com.ntaxeidis.blog.service.CommentService;
import com.ntaxeidis.blog.service.PostService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ntaxeidis.blog.model.dto.PostDto;

import java.util.List;

@Controller
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
	private final CommentService commentService;
	private final PostService postService;

	@GetMapping(value = "/{id}/comments")
	@ResponseStatus(HttpStatus.OK)
	public List<CommentDto> getComments(@PathVariable Long id) {
		return commentService.getCommentsForPost(id);
	}

	@PostMapping(value = "/{id}/comment")
	@ResponseStatus(HttpStatus.CREATED)
	public Long saveComment(@PathVariable Long id, @RequestBody NewCommentDto newComment) {
		return commentService.addComment(id, newComment);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long savePost(@RequestBody NewPostDto newPost) {
		return postService.addPost(newPost);
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PostDto getPost(@PathVariable Long id) {
		return postService.getPost(id);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.RESET_CONTENT)
	public void deletePost(@PathVariable Long id) {
		postService.deletePost(id);
	}
}
