package br.com.compasso.pautas.form;

import javax.validation.constraints.DecimalMin;

import com.sun.istack.NotNull;

import br.com.compasso.pautas.model.OptionVote;

public class VoteForm {
	@NotNull @DecimalMin(value = "1")
	private Long userId;
	
	@NotNull @DecimalMin(value = "1")
	private Long pollId;
	
	@NotNull
	private OptionVote userVote;

	public long getUserId() {
		return userId;
	}

	public long getPollId() {
		return pollId;
	}

	public OptionVote getUserVote() {
		return userVote;
	}

}
