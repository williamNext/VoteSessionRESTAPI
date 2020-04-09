package br.com.compasso.pautas.converter;

import org.springframework.stereotype.Component;

import br.com.compasso.pautas.controller.dto.PollSessionDto;
import br.com.compasso.pautas.converter.builder.PollSessionDtoBuilder;
import br.com.compasso.pautas.model.PollSession;

@Component
public class PollSessionToPollSessionDTOConverter implements EntityToDtoConverter<PollSession,PollSessionDto>{

	@Override
	public PollSessionDto convertToDTO(PollSession pollSession) {
		 PollSessionDto pollSessionDto = new PollSessionDtoBuilder()
				 						.setId(pollSession.getId())
										.setAllVotesCount(pollSession.getVotes().size())
								 		.setCreationDate(pollSession.getCreationDate())
								 		.setFinishDate(pollSession.getFinishDate())
								 		.setNo_votesCount(pollSession.countNoVotes())
								 		.setYes_VotesCount(pollSession.countYesVotes())
								 		.setSubject(pollSession.getPoll().getSubject())
								 		.setSubjectDescription(pollSession.getPoll().getDescription())
								 		.setPollStatus(pollSession.getStatus())
								 		.build();
		return pollSessionDto;
	}

	public PollSessionDto convertToDTOonCreate(PollSession pollSession) {
		 PollSessionDto pollSessionDto = new PollSessionDtoBuilder()
				 						.setId(pollSession.getId())
								 		.setCreationDate(pollSession.getCreationDate())
								 		.setFinishDate(pollSession.getFinishDate())
								 		.setSubject(pollSession.getPoll().getSubject())
								 		.setAllVotesCount(0)
								 		.setNo_votesCount(0l)
								 		.setYes_VotesCount(0l)
								 		.setSubjectDescription(pollSession.getPoll().getDescription())
								 		.setPollStatus(pollSession.getStatus())
								 		.build();
		return pollSessionDto;
	}

	
}