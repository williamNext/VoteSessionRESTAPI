package br.com.compasso.pautas.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.compasso.pautas.controller.dto.PollSessionDto;
import br.com.compasso.pautas.converter.PollSessionToPollSessionDTOConverter;
import br.com.compasso.pautas.model.Poll;
import br.com.compasso.pautas.model.PollSession;
import br.com.compasso.pautas.repository.PollSessionRepository;
import javassist.NotFoundException;

@Service
public class PollSessionService {
	
	private static final long DEFAULT_TIME_SESSION= 1000;
	@Autowired
	private PollSessionRepository pollSessionRepository;
	
	@Autowired
	private PollSessionToPollSessionDTOConverter converter;
	
	public List<PollSessionDto> getAll() throws NotFoundException {	
		List<PollSession> allPolls = pollSessionRepository.findAll();
		
		List<PollSessionDto> pollSessionDtoList = new ArrayList<PollSessionDto>();
		
		if(!allPolls.isEmpty()) {
			allPolls.forEach(pollSession-> pollSessionDtoList.add(converter.convertToDTO(pollSession)));
			return pollSessionDtoList;
		}
		
		throw new NotFoundException("no polls found");
	}
	
	public PollSessionDto getById(Long id){
		Optional<PollSession> poll = pollSessionRepository.findById(id);
		
		 return poll.map(converter::convertToDTO).orElseThrow(NoSuchElementException::new);
	}

	public void openSession(Poll poll) {
		PollSession pollSession = new PollSession(1000l);
		pollSession.setPoll(poll);
		
	}
	

	public boolean isOpenToVote(PollSession pollSession){
			boolean isOpen = pollSession.getFinishDate().isBefore(LocalDateTime.now());
			return isOpen;
	}
	
	
}
