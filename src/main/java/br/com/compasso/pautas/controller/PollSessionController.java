package br.com.compasso.pautas.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.pautas.controller.dto.PollSessionDto;
import br.com.compasso.pautas.form.PollSessionForm;
import br.com.compasso.pautas.form.VoteForm;
import br.com.compasso.pautas.model.Poll;
import br.com.compasso.pautas.model.PollSession;
import br.com.compasso.pautas.model.Vote;
import br.com.compasso.pautas.repository.PollSessionRepository;
import br.com.compasso.pautas.repository.UserRepository;
import br.com.compasso.pautas.service.PollSessionService;
import br.com.compasso.pautas.service.VoteService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/sessionpoll")
public class PollSessionController {
	
	private final PollSessionService pollSessionService;
	
    private final UserRepository userRepository;
    
    private final PollSessionRepository pollSessionRepository;
    
	private VoteService voteService;

	
	public PollSessionController(PollSessionService pollSessionService, UserRepository userRepository
			,VoteService voteService,PollSessionRepository pollSessionRepository) {
			this.pollSessionService=pollSessionService;
			this.userRepository=userRepository;
			this.voteService = voteService;
			this.pollSessionRepository = pollSessionRepository;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> createPoll( @RequestBody @Valid PollSessionForm form){
		Poll poll = form.converter();
		new PollSession(form.getSessionDurationTime());
		
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping
	public ResponseEntity<List<PollSessionDto>> getAllPolls() throws NotFoundException {
		List<PollSessionDto> all = pollSessionService.getAll();
		return ResponseEntity.ok(all);	
	}
	
	@PostMapping
	@RequestMapping("vote")
	public void vote(@RequestBody @Valid VoteForm form) throws NotFoundException {
		PollSession pollSession = pollSessionRepository.getOne(form.getPollId());
		
		boolean isOpenToVote = pollSessionService.isOpenToVote(pollSession);
		
		
		if (isOpenToVote) {
				Vote vote = voteService.validateVote(form);
				voteService.tryToVote(vote, pollSession);
		}
	
	}
	
	
	
	
}
