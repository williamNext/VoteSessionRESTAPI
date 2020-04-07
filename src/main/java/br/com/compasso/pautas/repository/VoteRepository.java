package br.com.compasso.pautas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compasso.pautas.model.Vote;
@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

}
