package br.com.compasso.pautas.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.pautas.controller.dto.VoteDto;
import br.com.compasso.pautas.converter.EntityToDtoConverter;
import br.com.compasso.pautas.converter.VoteToVoteDto;
import br.com.compasso.pautas.form.VoteForm;
import br.com.compasso.pautas.model.PollSession;
import br.com.compasso.pautas.model.Vote;
import br.com.compasso.pautas.service.PollSessionService;
import br.com.compasso.pautas.service.VoteService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/vote")
public class VoteController {

	private final PollSessionService pollSessionService;
	private final VoteService voteService;
	private final EntityToDtoConverter<Vote, VoteDto> converter;

	public VoteController(PollSessionService pollSessionService,
			VoteService voteService,VoteToVoteDto toVoteDto) {
		this.pollSessionService = pollSessionService;
		this.voteService = voteService;
		this.converter = toVoteDto;
	}



	@PostMapping
	public ResponseEntity<VoteDto> vote(@RequestBody @Valid VoteForm form) throws NotFoundException {
		PollSession pollSession = pollSessionService.getById(form.getPollId());
		
		voteService.validateSession(pollSession, pollSessionService);
		
		Vote vote = voteService.createVote(form); 
		PollSession pollsessionVoted = voteService.tryToVote(pollSession,vote);
		
		pollSessionService.savePoll(pollsessionVoted);;
		
		return ResponseEntity.ok(converter.convertToDTO(vote));
	}

	
	
	
}
