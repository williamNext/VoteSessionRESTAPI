package br.com.compasso.pautas.controller.dto;

import br.com.compasso.pautas.model.OptionVote;

public class VoteDto {
	private Long userId;
	private OptionVote vote;
	private String userName;
	
	
	public VoteDto(Long userId, OptionVote vote, String userName) {
		super();
		this.userId = userId;
		this.vote = vote;
		this.userName = userName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public OptionVote getVote() {
		return vote;
	}
	public void setVote(OptionVote vote) {
		this.vote = vote;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
}
