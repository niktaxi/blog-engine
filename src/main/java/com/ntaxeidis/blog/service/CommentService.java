package com.ntaxeidis.blog.service;

import com.ntaxeidis.blog.exception.CommentNotFoundException;
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
	 * Creates a new comment
	 *
	 * @param newCommentDto data of new comment
	 * @return id of the created comment
	 *
	 * @throws IllegalArgumentException if there is no blog post for passed newCommentDto.postId
	 */
	public Long addComment(Long postId, NewCommentDto newCommentDto) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new PostNotFoundException(postId));

		Comment newComment = new Comment(newCommentDto.getContent(), newCommentDto.getAuthor());
		newComment.setPost(post);

		Comment savedComment = commentRepository.save(newComment);
		return savedComment.getId();
	}

	public void deleteComment(Long id) {
		Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));

		commentRepository.delete(comment);
	}

	public void editComment(Long id, NewCommentDto newCommentDto) {
		Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));

		if (newCommentDto.getAuthor() != null) {
			comment.setAuthor(newCommentDto.getAuthor());
		}
		if (newCommentDto.getContent() != null) {
			comment.setContent(newCommentDto.getContent());
		}
		commentRepository.save(comment);
	}

	public CommentDto getComment(Long id) {
		Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
		return CommentDto.builder()
			.id(comment.getId())
			.modifiedDate(comment.getModifiedDate())
			.creationDate(comment.getCreationDate())
			.likes(comment.getLikes())
			.content(comment.getContent())
			.thread(comment.getThread())
			.author(comment.getAuthor())
			.build();
	}

	public Long likeComment(Long id) {
		return commentRepository.findById(id)
			.map(c -> {
				c.setLikes(c.getLikes()+1);
				commentRepository.save(c);
				return c.getLikes();
			})
			.orElseThrow(() -> new CommentNotFoundException(id));
	}

	public Long replyToComment(Long id, NewCommentDto newCommentDto) {
		Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));

		comment.getThread().add(new Comment(newCommentDto.getContent(), newCommentDto.getAuthor()));
		commentRepository.save(comment);

		return comment.getThread().get(comment.getThread().size()-1).getId();
	}
}
