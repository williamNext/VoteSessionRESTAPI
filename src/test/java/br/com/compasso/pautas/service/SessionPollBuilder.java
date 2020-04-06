package br.com.compasso.pautas.service;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.OneToOne;

import br.com.compasso.pautas.model.Poll;
import br.com.compasso.pautas.model.PollSession;
import br.com.compasso.pautas.model.Vote;

public class SessionPollBuilder {
	private Long id;
	
	private boolean isOpenToVote;
	
	private LocalDateTime creationDate;
	
	private LocalDateTime finishDate;
	

	private Set<Vote> votes;
	
	@OneToOne
	private Poll poll;

	public Long getId() {
		return id;
	}

	public SessionPollBuilder setId(Long id) {
		this.id = id;
		return this;
	}
is
	}


	public SessionPollBuilder setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	public SessionPollBuilder setFinishDate(LocalDateTime finishDate) {
		this.finishDate = finishDate;
		return this;
	}

	

	public SessionPollBuilder setVotes(Set<Vote> votes) {
		this.votes = votes;
		return this;
	}



	public SessionPollBuilder setPoll(Poll poll) {
		this.poll = poll;
		return this;
		
	}
	
	public void build() {
		new PollSession(this.isOpenToVote,this.poll,this.creationDate,this.finishDate,this.votes);
	}
	

	
}
