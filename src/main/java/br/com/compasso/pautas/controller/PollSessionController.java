package br.com.compasso.pautas.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.pautas.controller.dto.PollSessionDto;
import br.com.compasso.pautas.converter.PollSessionToPollSessionDTOConverter;
import br.com.compasso.pautas.form.PollSessionForm;
import br.com.compasso.pautas.model.PollSession;
import br.com.compasso.pautas.repository.PollRepository;
import br.com.compasso.pautas.service.PollSessionService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/pollSession")
public class PollSessionController {
	
	private final PollSessionService pollSessionService;
	
    

    private final PollRepository pollRepository;
	
	
	
	private final PollSessionToPollSessionDTOConverter pollSessionDtoConverter;




	public PollSessionController(PollSessionService pollSessionService,
			PollSessionToPollSessionDTOConverter pollSessionDtoConverter,
			PollRepository pollRepository) {
		
			this.pollRepository =pollRepository;
			this.pollSessionDtoConverter =pollSessionDtoConverter;
			this.pollSessionService =pollSessionService;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PollSessionDto> createPoll( @RequestBody @Valid PollSessionForm form,  UriComponentsBuilder uriBuilder){
		PollSession pollSession = form.converter(pollRepository);
		pollSessionService.savePoll(pollSession);		
		
		URI uri = uriBuilder.path("/pollSession/{id}").buildAndExpand(pollSession.getId()).toUri();
		return ResponseEntity.created(uri).body(pollSessionDtoConverter.convertToDTOonCreate(pollSession));	
		
	}
	
	@GetMapping
	public ResponseEntity<List<PollSessionDto>> getAllPolls() throws NotFoundException {
		List<PollSessionDto> all = pollSessionService.getAll();
		return ResponseEntity.ok(all);	
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PollSessionDto> getOne(@PathVariable Long id) {
		PollSession sessionById = pollSessionService.getById(id);

		return ResponseEntity.ok(pollSessionDtoConverter.convertToDTO(sessionById));
		
	}

	
	
}
