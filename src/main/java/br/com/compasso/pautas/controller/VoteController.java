package br.com.compasso.pautas.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.pautas.form.VoteForm;
import br.com.compasso.pautas.model.PollSession;
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

	
	
	public VoteController(PollSessionService pollSessionService, PollSessionRepository pollSessionRepository,
			VoteService voteService) {
		this.pollSessionService = pollSessionService;
		this.pollSessionRepository = pollSessionRepository;
		this.voteService = voteService;
	}



	@PostMapping
	public ResponseEntity<?> vote(@RequestBody @Valid VoteForm form) throws NotFoundException {
		Optional<PollSession> pollSession = pollSessionRepository.findById(form.getPollId());
		
		
		voteService.validateSession(pollSession, pollSessionService);
		PollSession pollsessionVoted = voteService.tryToVote(pollSession.get(),form);
		
		pollSessionRepository.save(pollsessionVoted);
		
		return ResponseEntity.ok().build();
	}
	
	
	
}
