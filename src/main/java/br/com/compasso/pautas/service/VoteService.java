package br.com.compasso.pautas.service;

import java.security.InvalidParameterException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.pautas.form.VoteForm;
import br.com.compasso.pautas.model.OptionVote;
import br.com.compasso.pautas.model.PollSession;
import br.com.compasso.pautas.model.User;
import br.com.compasso.pautas.model.Vote;
import br.com.compasso.pautas.repository.UserRepository;
@Service
public class VoteService {
	@Autowired
	
	private UserRepository userRepository;

	public Vote validateVote(@Valid VoteForm form) {
		User user = userRepository.getOne(form.getUserId());
		Vote vote = new Vote();
		vote.setUser(user);
		
		if (form.getUserVote().toUpperCase().equals("YES"))
			vote.setOptionVote(OptionVote.YES);
		else 
			vote.setOptionVote(OptionVote.NO);
		
		return vote;
		
	}
	
    public void tryToVote(Vote vote, PollSession pollSession) {
    		if (!pollSession.addVote(vote))
    			throw new InvalidParameterException();
    }
	
	
}
