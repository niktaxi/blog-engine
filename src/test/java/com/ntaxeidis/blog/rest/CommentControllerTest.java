package com.ntaxeidis.blog.rest;

import com.ntaxeidis.blog.model.dto.CommentDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import org.springframework.http.MediaType;

import com.ntaxeidis.blog.model.dto.NewCommentDto;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CommentControllerTest extends AbstractControllerTest {

	@Test
	public void shouldDeleteComment() throws Exception {
		// when
		doNothing().when(commentService).deleteComment(1L);

		// then
		mockMvc.perform(delete("/comments/1").accept(APPLICATION_JSON_UTF8))
			.andExpect(status().isResetContent());
	}

}
