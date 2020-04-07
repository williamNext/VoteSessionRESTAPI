package br.com.compasso.pautas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compasso.pautas.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
		
	Optional<User>findById(Long id);
}
