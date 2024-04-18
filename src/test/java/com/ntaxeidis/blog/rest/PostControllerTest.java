package com.ntaxeidis.blog.rest;

import com.ntaxeidis.blog.model.dto.CommentDto;
import com.ntaxeidis.blog.model.dto.NewCommentDto;
import com.ntaxeidis.blog.model.dto.PostDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostControllerTest extends AbstractControllerTest {

	@Test
	public void shouldReturnFoundPost() throws Exception {
		// given
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		LocalDateTime modifiedDate = LocalDateTime.of(2018, 5, 20, 21, 12, 43);
		PostDto post = PostDto.builder()
			.title("Title")
			.content("content")
			.creationDate(creationDate)
			.modifiedDate(modifiedDate)
			.build();

		// when
		when(postService.getPost(1L)).thenReturn(post);

		// then
		mockMvc.perform(get("/posts/1").accept(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.title", is("Title")))
				.andExpect(jsonPath("$.content", is("content")))
				.andExpect(jsonPath("$.creationDate", is(creationDate.toString())))
				.andExpect(jsonPath("$.modifiedDate", is(modifiedDate.toString())));
	}

	@Test
	public void shouldDeletePost() throws Exception {
		// when
		doNothing().when(postService).deletePost(1L);

		// then
		mockMvc.perform(delete("/posts/1").accept(APPLICATION_JSON_UTF8))
			.andExpect(status().isResetContent());
	}

	@Test
	public void shouldReturnFoundComments() throws Exception {

		// given
		List<CommentDto> comments = new ArrayList<>();
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		LocalDateTime modifiedDate = LocalDateTime.of(2018, 5, 25, 13, 21, 34);
		comments.add(CommentDto.builder()
			.id(2L)
			.content("John Smith")
			.likes(0L)
			.creationDate(creationDate)
			.modifiedDate(modifiedDate)
			.build());
		PostDto post = PostDto.builder()
			.comments(comments)
			.title("Titlos")
			.content("Post content")
			.build();

		// when
		when(postService.getPost(1L)).thenReturn(post);

		// then
		mockMvc.perform(get("/posts/1").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].title", is("Titlos")))
			.andExpect(jsonPath("$[0].content", is("Post content")))
			.andExpect(jsonPath("$[0].comments[0].id", is(2)))
			.andExpect(jsonPath("$[0].comments[0].comment", is("comment content")))
			.andExpect(jsonPath("$[0].comments[0].author", is("John Smith")))
			.andExpect(jsonPath("$[0].comments[0].creationDate", is(creationDate.toString())))
			.andExpect(jsonPath("$[0].comments[0].modifiedDate", is(modifiedDate.toString())));

	}

	@Test
	public void shouldAddComment() throws Exception {

		// given
		String commentBody = "{\"content\":\"Test content\", \"author\":\"John Doe\"}";
		NewCommentDto newComment = createComment("Test content", "John Doe");

		// when
		when(commentService.addComment(1L, newComment)).thenReturn(1L);

		// then
		mockMvc.perform(post("/posts/1/comment")
				.content(commentBody)
				.contentType(APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());
	}

	private NewCommentDto createComment(String content, String author) {
		NewCommentDto newComment = new NewCommentDto();
		newComment.setContent(content);
		newComment.setAuthor(author);
		return newComment;
	}
}
