package br.com.compasso.pautas.converter.builder;

import br.com.compasso.pautas.controller.dto.VoteDto;
import br.com.compasso.pautas.model.OptionVote;

public class VoteDtoBuilder {
	
	private Long userId;
	private OptionVote vote;
	private String userName;
	
	

	
	public VoteDtoBuilder setUserId(Long userId) {
		this.userId = userId;
		return this;
	}
	
	public VoteDtoBuilder setVote(OptionVote vote) {
		this.vote = vote;
		return this;
	}
	
	public VoteDtoBuilder setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	public VoteDto build() {
		return new VoteDto(userId, vote, userName);
	}
	
}
