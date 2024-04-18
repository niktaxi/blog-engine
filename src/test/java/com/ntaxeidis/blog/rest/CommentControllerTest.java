package com.ntaxeidis.blog.rest;

import org.junit.Test;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
