package br.com.compasso.pautas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import Utils.VotesAndPollGetter;
import br.com.compasso.pautas.controller.dto.PollSessionDto;
import br.com.compasso.pautas.converter.PollSessionToPollSessionDTOConverter;
import br.com.compasso.pautas.model.PollSession;
import br.com.compasso.pautas.repository.PollSessionRepository;
import javassist.NotFoundException;

@RunWith(MockitoJUnitRunner.class)
class PollSessionServiceTest {
//		
		@InjectMocks
		private  PollSessionService pollsessionService;
		
		@Mock
		private PollSessionToPollSessionDTOConverter pollSessionToPollSessionDto;
		
		@Mock
		private PollSessionRepository pollSessionRepository;
		
		@Mock
		private PollSessionDto pollSessionDto;
		
		@BeforeEach
		public void setUp() {
			MockitoAnnotations.initMocks(this);
		}
		
		@Test
		public void GetAllPollSession() throws NotFoundException {
			VotesAndPollGetter VotesAndPollGetter = new VotesAndPollGetter();
			
			PollSession pollSession = new PollSession();
			pollSession.setId(1l);
			pollSession.setPoll(VotesAndPollGetter.getPoll());
			pollSession.setVotes(VotesAndPollGetter.getVotes());
			
			
			List<PollSession> simulacrum= Arrays.asList(pollSession,pollSession,pollSession,pollSession);
			
			when(pollSessionRepository.findAll()).thenReturn(simulacrum);
			
			List<PollSession> realList = pollsessionService.getAll();
			
			
			
			assertEquals(simulacrum, realList);
			
				
		}


}