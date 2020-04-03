package br.com.compasso.pautas.converter;

import java.time.LocalDateTime;

import br.com.compasso.pautas.controller.dto.PollSessionDto;

public class PollSessionDtoBuilder {
	private String subject;
	private String subjectDescription;
	private Integer allVotesCount;
	private Integer yes_VotesCount;
	private Integer no_votesCount;
	private LocalDateTime creationDate;

	public PollSessionDtoBuilder setSubject(String subject) {
		this.subject = subject;
		return this;
	}

	public PollSessionDtoBuilder setSubjectDescription(String subjectDescription) {
		this.subjectDescription = subjectDescription;
		return this;
	}

	public PollSessionDtoBuilder setAllVotesCount(Integer allVotesCount) {
		this.allVotesCount = allVotesCount;
		return this;
	}

	public PollSessionDtoBuilder setYes_VotesCount(Long yes_VotesCount) {
		this.yes_VotesCount = yes_VotesCount.intValue();
		return this;
	}

	public PollSessionDtoBuilder setNo_votesCount(Long no_votesCount) {
		this.no_votesCount = no_votesCount.intValue();
		return this;
	}

	public PollSessionDtoBuilder setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
		return this;
	}
	
	public PollSessionDto build() {
		return new PollSessionDto(subject, subjectDescription, allVotesCount, yes_VotesCount, no_votesCount, creationDate);
	}

}
