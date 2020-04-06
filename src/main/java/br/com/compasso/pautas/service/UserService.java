package br.com.compasso.pautas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.compasso.pautas.controller.dto.UserDto;
import br.com.compasso.pautas.converter.UserToUserDto;
import br.com.compasso.pautas.model.User;
import br.com.compasso.pautas.repository.UserRepository;
@Component
public class UserService {
	
	@Autowired
	private UserToUserDto toUserDto;
	
	@Autowired
	private UserRepository userRepository;
		
	public Optional<UserDto> persistUser(User user){
		userRepository.save(user);
		
		return Optional.of(toUserDto.convertToDTO(user));
		}
	
}
