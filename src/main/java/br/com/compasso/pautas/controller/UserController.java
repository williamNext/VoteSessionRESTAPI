package br.com.compasso.pautas.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.pautas.controller.dto.UserDto;
import br.com.compasso.pautas.converter.UserToUserDto;
import br.com.compasso.pautas.form.UserForm;
import br.com.compasso.pautas.model.User;
import br.com.compasso.pautas.repository.UserRepository;
import br.com.compasso.pautas.service.UserService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/associate")
public class UserController {
	
	private final UserService userService;
	private final UserRepository userRepository;
	private UserToUserDto userToUserDto;
	
	
	public UserController(UserService userService, UserRepository userRepository, UserToUserDto userToUserDto) {
		this.userService = userService;
		this.userRepository = userRepository;
		this.userToUserDto =userToUserDto;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UserDto> saveUser(@RequestBody @Valid UserForm form,  UriComponentsBuilder uriBuilder) throws NotFoundException {
		User user = form.convert();
		userRepository.save(user);
		
		URI uri = uriBuilder.path("/associate/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(userToUserDto.convertToDTO(user));	
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> allUsers = userService.getAll();
		return ResponseEntity.ok(allUsers);
	}
}
