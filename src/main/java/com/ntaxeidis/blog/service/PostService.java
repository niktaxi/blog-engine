package com.ntaxeidis.blog.service;


import com.ntaxeidis.blog.exception.PostNotFoundException;
import com.ntaxeidis.blog.model.Post;
import com.ntaxeidis.blog.model.dto.CommentDto;
import com.ntaxeidis.blog.model.dto.NewPostDto;
import org.springframework.stereotype.Service;

import com.ntaxeidis.blog.model.dto.PostDto;
import com.ntaxeidis.blog.repository.PostRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public PostDto getPost(Long id) {

		return postRepository.findById(id)
				.map(post -> PostDto.builder()
						.content(post.getContent())
						.creationDate(post.getCreationDate())
						.modifiedDate(post.getModifiedDate())
						.title(post.getTitle())
						.likes(post.getLikes())
						.comments(post.getComments().stream()
							.map(comment -> CommentDto.builder()
								.author(comment.getAuthor())
								.content(comment.getContent())
								.id(comment.getId())
								.likes(comment.getLikes())
								.thread(comment.getThread())
								.creationDate(comment.getCreationDate())
								.modifiedDate(comment.getModifiedDate())
								.build())
							.sorted(Comparator.comparing(CommentDto::getCreationDate))
							.collect(Collectors.toList()))
						.build())
				.orElseThrow(() -> new PostNotFoundException(id));
	}

    public void deletePost(Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		postRepository.delete(post);
    }

	public Long addPost(NewPostDto newPost) {
		Post savedPost = postRepository.save(new Post(newPost.getTitle(), newPost.getContent()));
		return savedPost.getId();
	}

	public void editPost(Long id, NewPostDto newPost) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
 		if (newPost.getContent() != null) {
			 post.setContent(newPost.getContent());
		}
		if (newPost.getTitle() != null) {
			post.setTitle(newPost.getTitle());
		}
		postRepository.save(post);
	}

	public Long likePost(Long id) {
		return postRepository.findById(id)
			.map(post -> {
				post.setLikes(post.getLikes()+1);
				postRepository.save(post);
				return post.getLikes();
			})
			.orElseThrow(() -> new PostNotFoundException(id));
	}
}
