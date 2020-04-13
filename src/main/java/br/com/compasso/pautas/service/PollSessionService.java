package br.com.compasso.pautas.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.compasso.pautas.controller.dto.PollSessionDto;
import br.com.compasso.pautas.converter.PollSessionToPollSessionDTOConverter;
import br.com.compasso.pautas.model.PollSession;
import br.com.compasso.pautas.repository.PollSessionRepository;
import javassist.NotFoundException;

@Service
public class PollSessionService {
	
	private final PollSessionRepository pollSessionRepository;
	
	private final PollSessionToPollSessionDTOConverter converter;
	
	public PollSessionService(PollSessionRepository pollSessionRepository,
			PollSessionToPollSessionDTOConverter converter) {
		this.pollSessionRepository = pollSessionRepository;
		this.converter = converter;
	}

	public List<PollSessionDto> getAll() throws NotFoundException {	
		List<PollSession> allPolls = pollSessionRepository.findAll();
		
		List<PollSessionDto> pollSessionDtoList = new ArrayList<PollSessionDto>();
		
		if(!allPolls.isEmpty()) {
			allPolls.forEach(pollSession-> pollSessionDtoList.add(converter.convertToDTO(pollSession)));
			return pollSessionDtoList;
		}
		
		throw new NotFoundException("no polls found");
	}
	
	
	public PollSession getById(Long id){
       Optional<PollSession> pollSession = pollSessionRepository.findById(id);
		
		if (!pollSession.isPresent()) 
			throw new NoSuchElementException("no such poll for the given id");
		
		return pollSession.get();
	}

	

	public boolean isOpenToVote(PollSession pollSession){
			boolean isOpen = pollSession.getFinishDate().isBefore(LocalDateTime.now());
			return isOpen;
	}
	
	
	public void savePoll(PollSession pollSession) {
		pollSessionRepository.save(pollSession);	
	}
	
	
}
