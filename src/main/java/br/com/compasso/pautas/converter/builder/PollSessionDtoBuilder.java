package br.com.compasso.pautas.converter.builder;

import java.time.LocalDateTime;

import br.com.compasso.pautas.controller.dto.PollSessionDto;
import br.com.compasso.pautas.model.PollStatus;

public class PollSessionDtoBuilder {

	private Long id;
	private String subject;
	private String subjectDescription;
	private Integer allVotesCount;
	private Integer yes_VotesCount;
	private Integer no_votesCount;
	private LocalDateTime creationDate;
	private LocalDateTime finishDate;
	private PollStatus pollStatus;


	public PollSessionDtoBuilder setId(Long id) {
		this.id = id;
		return this;
	}
	
	public PollStatus getPollStatus() {
		return pollStatus;
	}

	public PollSessionDtoBuilder setPollStatus(PollStatus pollStatus) {
		this.pollStatus = pollStatus;
		return this;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public LocalDateTime getFinishDate() {
		return finishDate;
	}

	public PollSessionDtoBuilder setFinishDate(LocalDateTime finishDate) {
		this.finishDate = finishDate;
		return this;
	}

	public PollSessionDtoBuilder setSubject(String subject) {
		this.subject = subject;
		return this;
	}

	public PollSessionDtoBuilder setSubjectDescription(String subjectDescription) {
		this.subjectDescription = subjectDescription;
		return this;
	}

	public PollSessionDtoBuilder setAllVotesCount(Integer allVotesCount) {
		if (allVotesCount != null)
			this.allVotesCount = allVotesCount;
		return this;
	}

	public PollSessionDtoBuilder setYes_VotesCount(Long yes_VotesCount) {
		if (yes_VotesCount != null)
			this.yes_VotesCount = yes_VotesCount.intValue();
		return this;
	}

	public PollSessionDtoBuilder setNo_votesCount(Long no_votesCount) {
		if (no_votesCount != null)
			this.no_votesCount = no_votesCount.intValue();
		return this;
	}

	public PollSessionDtoBuilder setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	public PollSessionDto build() {
		return new PollSessionDto(subject, subjectDescription, allVotesCount, yes_VotesCount, no_votesCount,
				creationDate, finishDate, pollStatus,id);
	}

}
