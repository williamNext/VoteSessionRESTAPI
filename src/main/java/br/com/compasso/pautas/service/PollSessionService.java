package br.com.compasso.pautas.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.compasso.pautas.model.PollSession;
import br.com.compasso.pautas.repository.PollSessionRepository;
import javassist.NotFoundException;

@Service
public class PollSessionService {

	private final PollSessionRepository pollSessionRepository;

	public PollSessionService(PollSessionRepository pollSessionRepository) {
		this.pollSessionRepository = pollSessionRepository;
	}

	public List<PollSession> getAll() throws NotFoundException {
        return pollSessionRepository.findAll();
	}

	public PollSession getById(Long id) {
		Optional<PollSession> pollSession = pollSessionRepository.findById(id);
		return pollSession.orElseThrow(()-> new NoSuchElementException("no such poll for the given id"));
	}

	public boolean isOpenToVote(PollSession pollSession) {
		return pollSession.getFinishDate().isBefore(LocalDateTime.now());
	}

	public void savePoll(PollSession pollSession) {
		pollSessionRepository.save(pollSession);
	}

}
