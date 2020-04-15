package br.com.compasso.pautas.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;

import br.com.compasso.pautas.controller.dto.PollSessionDto;
import br.com.compasso.pautas.converter.PollSessionToPollSessionDTOConverter;
import br.com.compasso.pautas.form.PollSessionForm;
import br.com.compasso.pautas.model.Poll;
import br.com.compasso.pautas.model.PollSession;
import br.com.compasso.pautas.repository.PollRepository;
import br.com.compasso.pautas.repository.PollSessionRepository;
import br.com.compasso.pautas.service.PollSessionService;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(PollSessionController.class)
class PollSessionControllerTest {
	
	@MockBean
	private PollSessionToPollSessionDTOConverter toDtoConverter;
	@MockBean
	private PollSessionRepository pollSessionRepository;
	@MockBean
	private PollSessionService pollSessionService;
	
	@MockBean
	private PollRepository pollRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void createPollTest() throws Exception {
		Gson gson = new Gson();
		
		var pollSessionForm = new PollSessionForm();
		pollSessionForm.setSessionDurationTime(2l);
		pollSessionForm.setSubject("este é um assunto");
		pollSessionForm.setSubjectDescription("essa é a descrição de um assunto");
	
		doNothing().when(pollSessionService).savePoll(any(PollSession.class));
		
		
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/pollSession")
				.content(gson.toJson(pollSessionForm))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(MockMvcResultMatchers.status().isCreated());
		
	}
	
	@Test
	void createPollBadRequestTest() throws Exception{

		mockMvc.perform(MockMvcRequestBuilders
				.post("/pollSession")
				.content("{}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

}
