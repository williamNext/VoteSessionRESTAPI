package br.com.compasso.pautas.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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

import br.com.compasso.pautas.controller.dto.VoteDto;
import br.com.compasso.pautas.converter.VoteToVoteDto;
import br.com.compasso.pautas.form.VoteForm;
import br.com.compasso.pautas.model.OptionVote;
import br.com.compasso.pautas.model.PollSession;
import br.com.compasso.pautas.model.User;
import br.com.compasso.pautas.model.Vote;
import br.com.compasso.pautas.service.PollSessionService;
import br.com.compasso.pautas.service.VoteService;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(VoteController.class)
class VoteControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private VoteService voteService;
	
	@MockBean
	private VoteToVoteDto toVoteDto;
	
	@MockBean
	private PollSessionService pollSessionService;
	
	@Test
	void TestVoteController() throws Exception {
		var user = new User("william","000000");
		user.setId(10l);
		
		var vote = new Vote(10l, OptionVote.YES, user);
		
		var voteForm = new VoteForm();
		voteForm.setUserId(10l);
		voteForm.setPollId(10l);
		voteForm.setUserVote(OptionVote.YES);
		
		
		var pollSession = new PollSession(2l);
		
		var gson = new Gson();
		
		var voteDto = new VoteDto(10l, OptionVote.YES, "william");
		
		
		when(pollSessionService.getById(anyLong())).thenReturn(pollSession);
		
		doNothing().when(voteService).validateSession(pollSession, pollSessionService);
		when(voteService.createVote(any(VoteForm.class))).thenReturn(vote);
		when(voteService.tryToVote(any(PollSession.class), any(Vote.class))).thenReturn(pollSession);
		doNothing().when(pollSessionService).savePoll(any(PollSession.class));
		when(toVoteDto.convertToDTO(any(Vote.class))).thenCallRealMethod();
		
		mockMvc.perform(MockMvcRequestBuilders
						.post("/vote")
						.content(gson.toJson(voteForm))
						 .contentType(MediaType.APPLICATION_JSON)
						 .accept(MediaType.APPLICATION_JSON)
						 ).andExpect(MockMvcResultMatchers.content().string(gson.toJson(voteDto)))
						  .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
