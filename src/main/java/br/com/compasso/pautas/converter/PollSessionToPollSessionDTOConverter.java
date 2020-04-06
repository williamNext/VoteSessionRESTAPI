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
										.setAllVotesCount(pollSession.getVotes().size())
								 		.setCreationDate(pollSession.getCreationDate())
								 		.setNo_votesCount(pollSession.countNoVotes())
								 		.setYes_VotesCount(pollSession.countYesVotes())
								 		.setSubject(pollSession.getPoll().getSubject())
								 		.setSubjectDescription(pollSession.getPoll().getDescription())
								 		.build();
		return pollSessionDto;
	}

	
}