package Utils;

import java.util.HashSet;

import br.com.compasso.pautas.model.OptionVote;
import br.com.compasso.pautas.model.Poll;
import br.com.compasso.pautas.model.User;
import br.com.compasso.pautas.model.Vote;

public class VotesAndPollGetter {
	
	private HashSet<Vote> votes;
	private Poll poll;

	public VotesAndPollGetter() {
		User user = new User("william" ,"00000");
		User user2 = new User("marcelo" ,"33333");
		User user3 = new User("lucas" ,"4444");
		user.setId(1l);
		user2.setId(2l);
		user3.setId(3l);
		
		Vote vote = new Vote(1l, OptionVote.YES, user3);
		Vote vote2 = new Vote(2l, OptionVote.YES, user2);
		Vote vote3 = new Vote(3l, OptionVote.NO, user);
		
		votes = new HashSet<Vote>();
		votes.add(vote);
		votes.add(vote2);
		votes.add(vote3);
		
		poll = new Poll();
		poll.setDescription("descripiton of poll");
		poll.setId(1l);
		poll.setSubject("subject of poll");
		
	}

	public HashSet<Vote> getVotes() {
		return votes;
	}

	public Poll getPoll() {
		return poll;
	}

	
	 
	
}
