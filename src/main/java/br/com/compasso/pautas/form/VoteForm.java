package br.com.compasso.pautas.form;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

public class VoteForm {
	@NotNull @DecimalMin(value = "1")
	private Long userId;
	
	@NotNull @DecimalMin(value = "1")
	private Long pollId;
	
	@NotBlank
	private String userVote;

	public long getUserId() {
		return userId;
	}

	public long getPollId() {
		return pollId;
	}

	public String getUserVote() {
		return userVote;
	}

}
