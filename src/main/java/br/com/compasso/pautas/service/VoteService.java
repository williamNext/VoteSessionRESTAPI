package br.com.compasso.pautas.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.pautas.exception.InvalidPollSessionException;
import br.com.compasso.pautas.form.VoteForm;
import br.com.compasso.pautas.model.PollSession;
import br.com.compasso.pautas.model.User;
import br.com.compasso.pautas.model.Vote;
import br.com.compasso.pautas.repository.UserRepository;
import br.com.compasso.pautas.repository.VoteRepository;
@Service
public class VoteService {
	@Autowired
	
	private UserRepository userRepository;
	
	@Autowired
	private VoteRepository voteRepository;

	
    public PollSession tryToVote(PollSession pollSession, Vote vote) {
    	pollSession.addVote(vote,voteRepository);
    	return pollSession;
    }
    
	public Vote createVote(VoteForm form) {
		Optional<User> user = userRepository.findById(form.getUserId());
		
		if(!user.isPresent()) {
			throw new NoSuchElementException("No such user for the given id");
		}
		Vote vote = new Vote();
		vote.setUser(user.get());
		vote.setOptionVote(form.getUserVote());
		
		return vote;
	}
	
	
	public void validateSession(PollSession pollSession,PollSessionService pollSessionService) {
			if(pollSessionService.isOpenToVote(pollSession)) { 
				throw new InvalidPollSessionException("Session is closed, voting its not allowed"); };	
	}	
	
}
