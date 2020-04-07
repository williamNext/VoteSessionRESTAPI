package br.com.compasso.pautas.service;

import java.util.ArrayList;
import java.util.List;
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

	public List<UserDto> getAll() {
		List<User> findAll = userRepository.findAll();
		List<UserDto> allUsersDto = new ArrayList<UserDto>();
		
		findAll.forEach(user->allUsersDto.add(toUserDto.convertToDTO(user)));
		
		return allUsersDto;
	}
	
}
