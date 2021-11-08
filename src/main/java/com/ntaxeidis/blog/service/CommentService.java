package com.ntaxeidis.blog.service;

import com.ntaxeidis.blog.exception.PostNotFoundException;
import com.ntaxeidis.blog.model.Comment;
import com.ntaxeidis.blog.model.Post;
import com.ntaxeidis.blog.repository.CommentRepository;
import com.ntaxeidis.blog.repository.PostRepository;
import java.util.Comparator;
import java.util.List;

import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.ntaxeidis.blog.model.dto.CommentDto;
import com.ntaxeidis.blog.model.dto.NewCommentDto;

@Service
public class CommentService {
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;

	public CommentService(PostRepository postRepository, CommentRepository commentRepository) {
		this.postRepository = postRepository;
		this.commentRepository = commentRepository;
	}

	/**
	 * Returns a list of all comments for a blog post with passed id.
	 *
	 * @param postId id of the post
	 * @return list of comments sorted by creation date descending - most recent first
	 */
	public List<CommentDto> getCommentsForPost(Long postId) {
		Post post = postRepository.findById(postId).get();

		return post.getComments().stream()
			.sorted(Comparator.comparing(Comment::getCreationDate).reversed())
			.map(c -> new CommentDto(c.getId(), c.getComment(), c.getAuthor(), c.getCreationDate()))
			.collect(Collectors.toList());
	}

	/**
	 * Creates a new comment
	 *
	 * @param newCommentDto data of new comment
	 * @return id of the created comment
	 *
	 * @throws IllegalArgumentException if there is no blog post for passed newCommentDto.postId
	 */
	public Long addComment(NewCommentDto newCommentDto) {
		Post post = postRepository.findById(newCommentDto.getPostId())
			.orElseThrow(() -> new PostNotFoundException(newCommentDto.getPostId()));

		Comment newComment = new Comment(newCommentDto.getContent(), newCommentDto.getAuthor());
		newComment.setPost(post);

		Comment savedComment = commentRepository.save(newComment);
		return savedComment.getId();
	}
}
