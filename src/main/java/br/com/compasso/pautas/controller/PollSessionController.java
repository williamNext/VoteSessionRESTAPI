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
import br.com.compasso.pautas.form.VoteForm;
import br.com.compasso.pautas.service.PollSessionService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/sessionpoll")
public class PollSessionController {
	
	private final PollSessionService pollSessionService;
	
	public PollSessionController(PollSessionService pollSessionService) {
			this.pollSessionService=pollSessionService;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> createPoll( @RequestBody @Valid VoteForm form){
		
		
		
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping
	public ResponseEntity<List<PollSessionDto>> getAllPolls() throws NotFoundException {
		List<PollSessionDto> all = pollSessionService.getAll();
		return ResponseEntity.ok(all);	
	}
	
	
	
	
}
