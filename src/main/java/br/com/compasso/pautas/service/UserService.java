package br.com.compasso.pautas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.pautas.model.User;
import br.com.compasso.pautas.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public void save(User user) {
		userRepository.save(user);
	}

}
