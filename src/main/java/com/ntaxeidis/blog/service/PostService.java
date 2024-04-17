package com.ntaxeidis.blog.service;


import com.ntaxeidis.blog.exception.PostNotFoundException;
import com.ntaxeidis.blog.model.Post;
import com.ntaxeidis.blog.model.dto.NewPostDto;
import org.springframework.stereotype.Service;

import com.ntaxeidis.blog.model.dto.PostDto;
import com.ntaxeidis.blog.repository.PostRepository;

@Service
public class PostService {

	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public PostDto getPost(Long id) {
		return postRepository.findById(id)
				.map(post -> new PostDto(post.getTitle(), post.getContent(), post.getCreationDate()))
				.orElse(null);
	}

    public void deletePost(Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		postRepository.delete(post);
    }

	public Long addPost(NewPostDto newPost) {
		Post savedPost = postRepository.save(new Post(newPost.getTitle(), newPost.getContent()));
		return savedPost.getId();
	}
}
