package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.example.demo.controller.QuizController;

@SpringBootTest
@AutoConfigureMockMvc
class QuizTestContoller {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	QuizController controller;

	@Test
	public void testQuizPage() throws Exception {
		this.mockMvc.perform(get("/quizapp"))
		.andExpect(status().isOk())
		.andExpect(view().name("quizapp/quiz"))
		.andDo(MockMvcResultHandlers.print())
		.andReturn();
	}

	@Test
	void testResultPage() throws Exception {
		for(int i=0 ; i < 10 ; i++) {
			mockMvc.perform(get("/quizapp")
					.contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk());
			
			if(i == 9) {
				this.mockMvc.perform(get("/quizapp"))
				.andExpect(status().isOk())
				.andExpect(view().name("quizapp/result"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
			}
		}

	}

}
