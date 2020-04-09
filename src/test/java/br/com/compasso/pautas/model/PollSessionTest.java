package br.com.compasso.pautas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.compasso.pautas.repository.VoteRepository;

class PollSessionTest {

	private Set<Vote> votes;
	private Vote DiferentVote;

	@BeforeEach
	void SetVotesBuild() {
		User user = new User("william" ,"00000");
		User user2 = new User("marcelo" ,"33333");
		User user3 = new User("lucas" ,"4444");
		User userDif = new User("diferente" ,"7777");
		user.setId(1l);
		user2.setId(2l);
		user3.setId(3l);
		userDif.setId(5l);
		
		Vote vote = new Vote(1l, OptionVote.YES, user3);
		Vote vote2 = new Vote(2l, OptionVote.YES, user2);
		Vote vote3 = new Vote(3l, OptionVote.NO, user);
		DiferentVote = new Vote(4l, OptionVote.NO, userDif);
		
		votes = new HashSet<Vote>();
		votes.add(vote);
		votes.add(vote2);
		votes.add(vote3);
	}
	
	@Test
	void countYesVoutes() {
		PollSession pollSession = new PollSession();
		pollSession.setVotes(votes);
		
		assertEquals(pollSession.countYesVotes(), 2);
	}
	@Test
	void countNoVoutes() {
		PollSession pollSession = new PollSession();
		pollSession.setVotes(votes);
		assertEquals(pollSession.countNoVotes(),1);
	}
	 
	@Test
	void addVotestest() {
		VoteRepository voteRepository = Mockito.mock(VoteRepository.class);
		PollSession pollSession = new PollSession();
		pollSession.setVotes(votes);
		
		pollSession.addVote(DiferentVote, voteRepository);
		assertEquals(pollSession.getVotes().size(),4);
	}
	
}
