package br.com.compasso.pautas.model;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Proxy;

import br.com.compasso.pautas.exception.UserNotPermitedException;
import br.com.compasso.pautas.repository.UserRepository;
import br.com.compasso.pautas.repository.VoteRepository;
import br.com.compasso.pautas.service.VoteService;

@Entity
public class PollSession {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private boolean isOpenToVote;
	
	private static final Long DEFAULT_INTERVAL = 1l;
	private LocalDateTime creationDate = LocalDateTime.now();
	private LocalDateTime finishDate = creationDate.plusMinutes(DEFAULT_INTERVAL);
	
	@Enumerated(EnumType.STRING)
	private PollStatus status = PollStatus.OPEN;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Vote> votes;
	
	@OneToOne
	private Poll poll;
	
	public PollSession(){}
	
	public PollSession(Long minutes) {
		if(minutes>1)
			finishDate = creationDate.plusMinutes(Optional.ofNullable(minutes).orElse(DEFAULT_INTERVAL));
		}

	public PollSession(boolean isOpenToVote2, Poll poll2, LocalDateTime creationDate2, LocalDateTime finishDate2,
			Set<Vote> votes2) {
			this.creationDate =creationDate2;
			this.finishDate =finishDate2;
			this.isOpenToVote= isOpenToVote2;
			this.poll =poll2;
			this.votes =votes2;
				}
	
	

	public PollStatus getStatus() {
		return status;
	}

	public void setStatus(PollStatus status) {
		this.status = status;
	}

	public LocalDateTime getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(LocalDateTime finishDate) {
		this.finishDate = finishDate;
	}


	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public boolean isOpenToVote() {
		return isOpenToVote;
	}

	public void setOpenToVote(boolean isValid) {
		this.isOpenToVote = isValid;
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
	
	public boolean addVote(Vote vote, VoteRepository voteRepository) {
		
		if(votes.add(vote)) {
			voteRepository.save(vote);
			return true;}
		else
			throw new UserNotPermitedException("this user already voted or is not permited");
	}
	

}
