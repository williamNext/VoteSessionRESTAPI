package br.com.compasso.pautas.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compasso.pautas.model.PollSession;
@Repository
public interface PollSessionRepository extends JpaRepository<PollSession, Long>{
		
	Optional<PollSession> findById(Long id);
	List<PollSession> findByFinishDateLessThan(LocalDateTime now);
;}
