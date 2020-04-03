package br.com.compasso.pautas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.pautas.model.PollSession;

public interface PollSessionRepository extends JpaRepository<PollSession, Long>{
		
	Optional<PollSession> findById(Long id);
}
