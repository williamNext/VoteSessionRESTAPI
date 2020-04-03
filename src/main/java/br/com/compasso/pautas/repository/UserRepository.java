package br.com.compasso.pautas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.compasso.pautas.model.User;

public interface UserRepository extends MongoRepository<User, Long>{
		
}
