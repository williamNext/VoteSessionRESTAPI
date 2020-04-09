package br.com.compasso.pautas.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.pautas.converter.VoteToVoteDto;
import br.com.compasso.pautas.form.VoteForm;
import br.com.compasso.pautas.model.PollSession;
import br.com.compasso.pautas.model.Vote;
import br.com.compasso.pautas.repository.PollSessionRepository;
import br.com.compasso.pautas.service.PollSessionService;
import br.com.compasso.pautas.service.VoteService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/vote")
public class VoteController {

	private final PollSessionService pollSessionService;
	private final PollSessionRepository pollSessionRepository;
	private final VoteService voteService;
	private final VoteToVoteDto toVoteDto;

	
	
	public VoteController(PollSessionService pollSessionService, PollSessionRepository pollSessionRepository,
			VoteService voteService,VoteToVoteDto toVoteDto) {
		this.pollSessionService = pollSessionService;
		this.pollSessionRepository = pollSessionRepository;
		this.voteService = voteService;
		this.toVoteDto = toVoteDto;
	}



	@PostMapping
	public ResponseEntity<?> vote(@RequestBody @Valid VoteForm form) throws NotFoundException {
		PollSession pollSession = pollSessionService.getById(form.getPollId());
		
		voteService.validateSession(pollSession, pollSessionService);
		
		Vote vote = voteService.createVote(form); 
		PollSession pollsessionVoted = voteService.tryToVote(pollSession,vote);
		
		pollSessionRepository.save(pollsessionVoted);
		
		return ResponseEntity.ok(toVoteDto.convertToDTO(vote));
	}

	
	
	
}
