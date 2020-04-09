package br.com.compasso.pautas.converter;

import org.springframework.stereotype.Component;

import br.com.compasso.pautas.controller.dto.VoteDto;
import br.com.compasso.pautas.converter.builder.VoteDtoBuilder;
import br.com.compasso.pautas.model.Vote;
@Component
public class VoteToVoteDto implements EntityToDtoConverter<Vote, VoteDto> {

	@Override
	public VoteDto convertToDTO(Vote vote) {
			VoteDto votedto = new VoteDtoBuilder()
					.setUserId(vote.getUser().getId())
					.setUserName(vote.getUser().getName())
					.setVote(vote.getOptionVote())
					.build();
		return votedto;
		
	}

}
