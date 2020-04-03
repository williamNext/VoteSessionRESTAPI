package br.com.compasso.pautas.model;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PollSession {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private boolean isValid;
	
	private LocalDateTime creationDate;
	private Set<Vote> votes;
	
	private Poll poll;
	
	
	

	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Set<Vote> getVotes() {
		return votes;
	}

	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
	public Long countYesVotes() {
		
		 long count = votes.stream()
				.filter(vote-> vote.getOptionVote()
				.equals(OptionVote.YES))
				.count();
		 
		 return count;
	}
	
	public long countNoVotes() {
		
		 long count = votes.stream()
				.filter(vote-> vote.getOptionVote()
				.equals(OptionVote.NO))
				.count();
		 
		 return count;
	}

}
