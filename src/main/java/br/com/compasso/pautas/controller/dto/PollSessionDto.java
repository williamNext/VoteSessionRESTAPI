package br.com.compasso.pautas.controller.dto;

import java.time.LocalDateTime;

public class PollSessionDto {

	private String subject;
	private String subjectDescription;
	private Integer allVotesCount;
	private Integer yes_VotesCount;
	private Integer no_votesCount;
	private LocalDateTime creationDate;
	private LocalDateTime finishDate;


	public PollSessionDto(String subject, String subjectDescription, Integer allVotesCount, Integer yes_VotesCount,
			Integer no_votesCount, LocalDateTime creationDate, LocalDateTime finishDate) {
		this.subject = subject;
		this.subjectDescription = subjectDescription;
		this.allVotesCount = allVotesCount;
		this.yes_VotesCount = yes_VotesCount;
		this.no_votesCount = no_votesCount;
		this.creationDate = creationDate;
		this.finishDate =finishDate;
	}

	public LocalDateTime getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(LocalDateTime finishDate) {
		this.finishDate = finishDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubjectDescription() {
		return subjectDescription;
	}

	public void setSubjectDescription(String subjectDescription) {
		this.subjectDescription = subjectDescription;
	}

	public Integer getAllVotesCount() {
		return allVotesCount;
	}

	public void setAllVotesCount(Integer allVotesCount) {
		this.allVotesCount = allVotesCount;
	}

	public Integer getYes_VotesCount() {
		return yes_VotesCount;
	}

	public void setYes_VotesCount(Integer yes_VotesCount) {
		this.yes_VotesCount = yes_VotesCount;
	}

	public Integer getNo_votesCount() {
		return no_votesCount;
	}

	public void setNo_votesCount(Integer no_votesCount) {
		this.no_votesCount = no_votesCount;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

}
