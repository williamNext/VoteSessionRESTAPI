package br.com.compasso.pautas.form;

import javax.validation.constraints.NotBlank;

import br.com.compasso.pautas.model.Poll;

public class PollSessionForm {
	@NotBlank
	private String subject;
	@NotBlank
	private String subjectDescription;
	
	private long  sessionDurationTime;

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

	public long getSessionDurationTime() {
		return sessionDurationTime;
	}

	public void setSessionDurationTime(long sessionDurationTime) {
		this.sessionDurationTime = sessionDurationTime;
	}

	public Poll converter() {
		// TODO Auto-generated method stub
		return new Poll(subject,subjectDescription);
	} 
	
	
}
