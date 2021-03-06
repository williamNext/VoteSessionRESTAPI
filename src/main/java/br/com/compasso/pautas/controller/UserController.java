package br.com.compasso.pautas.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import br.com.compasso.pautas.converter.EntityToDtoConverter;
import br.com.compasso.pautas.converter.UserToUserDto;
import br.com.compasso.pautas.form.UserForm;
import br.com.compasso.pautas.model.User;
import br.com.compasso.pautas.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@RestController
@RequestMapping("/associate")
public class UserController {
	
	private final UserService userService;
	private EntityToDtoConverter<User, UserDto> converter;
	
	
	public UserController(UserService userService, UserToUserDto userToUserDto) {
		this.userService = userService;
		this.converter =userToUserDto;
	}
	
	@PostMapping
	@Transactional
	@ApiOperation(value = "Create a new user")
	@ApiResponses(value = {@ApiResponse(code =201, message = "user created with sucess")})
	public ResponseEntity<UserDto> saveUser(@RequestBody @Valid UserForm form,  UriComponentsBuilder uriBuilder) throws NotFoundException {
		User user = form.convert();
		userService.save(user);
		
		URI uri = uriBuilder.path("/associate/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(converter.convertToDTO(user));	
	}
	
	
	@ApiOperation(value = "return all associates")
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<User> allUsers = userService.getAll();
		return ResponseEntity.ok(allUsers.stream().map(converter::convertToDTO).collect(Collectors.toList()));
	}
}
