package com.ntaxeidis.blog.rest;

import com.ntaxeidis.blog.model.dto.CommentDto;
import com.ntaxeidis.blog.model.dto.NewCommentDto;
import com.ntaxeidis.blog.model.dto.NewPostDto;
import com.ntaxeidis.blog.service.CommentService;
import com.ntaxeidis.blog.service.PostService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ntaxeidis.blog.model.dto.PostDto;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
	private final CommentService commentService;
	private final PostService postService;

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

	@PostMapping(value = "/{id}/like")
	@ResponseStatus(HttpStatus.CREATED)
	public Long likePost(@PathVariable Long id) {
		return postService.likePost(id);
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

	@PatchMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void editPost(@PathVariable Long id, @RequestBody NewPostDto newPost) {
		postService.editPost(id, newPost);
	}
}
