package com.ntaxeidis.blog.rest;

import com.ntaxeidis.blog.service.PostService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ntaxeidis.blog.model.dto.PostDto;

@Controller
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

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
