package br.com.compasso.pautas.service;

import java.security.InvalidParameterException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.pautas.exception.InvalidPollSessionException;
import br.com.compasso.pautas.form.VoteForm;
import br.com.compasso.pautas.model.OptionVote;
import br.com.compasso.pautas.model.PollSession;
import br.com.compasso.pautas.model.User;
import br.com.compasso.pautas.model.Vote;
import br.com.compasso.pautas.repository.UserRepository;
import br.com.compasso.pautas.repository.VoteRepository;
import net.bytebuddy.implementation.bytecode.Throw;
@Service
public class VoteService {
	@Autowired
	
	private UserRepository userRepository;
	
	@Autowired
	private VoteRepository voteRepository;

	
    public PollSession tryToVote(PollSession pollSession, @Valid VoteForm form) {
    		Optional<User> user = userRepository.findById(form.getUserId());
    		Vote vote;
    		
    		if(user.isPresent()) {
    			vote = createVote(form, user); 
    			pollSession.addVote(vote,voteRepository);
    		}
    		
    	return pollSession;
    }
    
	private Vote createVote(VoteForm form, Optional<User> user) {
		Vote vote = new Vote();
		vote.setUser(user.get());
		
		OptionVote optionVote = Stream.of(OptionVote.values())
				.filter(value-> value.toString().equals(form.getUserVote().toUpperCase()))
				.findAny().orElseThrow();
		
		vote.setOptionVote(optionVote);
		
		return vote;
	}
	
	
	public void validateSession(Optional<PollSession> pollSession,PollSessionService pollSessionService) {
		boolean isOpenToVote;
		
		if(pollSession.isPresent()) {
			if(pollSessionService.isOpenToVote(pollSession.get())) { 
				throw new InvalidPollSessionException("Session is closed, voting its not allowed"); };	
		}
		
	}	
	
}
