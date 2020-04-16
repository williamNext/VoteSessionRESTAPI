package br.com.compasso.pautas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.compasso.pautas.exception.InvalidPollSessionException;
import br.com.compasso.pautas.form.VoteForm;
import br.com.compasso.pautas.model.OptionVote;
import br.com.compasso.pautas.model.PollSession;
import br.com.compasso.pautas.model.User;
import br.com.compasso.pautas.model.Vote;
import br.com.compasso.pautas.repository.UserRepository;
import br.com.compasso.pautas.repository.VoteRepository;

@RunWith(MockitoJUnitRunner.class)
class VoteServiceTest {
	
	@InjectMocks
	private VoteService voteService;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private VoteRepository voteRepository;
	
	@Mock
	private PollSessionService pollSessionService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void CreateValidVotetest() {
		var user = new User("william", "00000");
		user.setId(1l);
		
		var voteForm = new VoteForm();
		voteForm.setPollId(1l);
		voteForm.setUserId(1l);
		voteForm.setUserVote(OptionVote.YES);
		
		var expectedVote = new Vote(1l, OptionVote.YES, user);
		
		
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
		
		var realVote = voteService.createVote(voteForm);
		
		assertEquals(expectedVote, realVote);
		
		
	}
	@Test
	void CreateInvalidVotetest() {

		
		var voteForm = new VoteForm();
		voteForm.setPollId(1l);
		voteForm.setUserId(1l);
		voteForm.setUserVote(OptionVote.YES);
		when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
		

		
		Assertions.assertThrows(NoSuchElementException.class, ()->voteService.createVote(voteForm));
	}
	
	@Test
	public void validateSessionInvalidSessionTest() {

		var pollSession = new PollSession();
		pollSession.setFinishDate(LocalDateTime.now().minusMinutes(30l));
	
		when(pollSessionService.isOpenToVote(any(PollSession.class))).thenCallRealMethod();

		Assertions.assertThrows(InvalidPollSessionException.class,
								()->voteService.validateSession(pollSession, pollSessionService));
	}
	

}
