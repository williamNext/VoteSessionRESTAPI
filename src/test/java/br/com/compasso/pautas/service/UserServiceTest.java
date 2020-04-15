package br.com.compasso.pautas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.compasso.pautas.controller.dto.UserDto;
import br.com.compasso.pautas.converter.UserToUserDto;
import br.com.compasso.pautas.model.User;
import br.com.compasso.pautas.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {

	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserToUserDto toUserDto;
	
	@Mock
	private UserRepository userRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	void testGetAllUsers() {
		List<User> entryList = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {
			User user = new User("user"+i, "000000");
			user.setId(Long.valueOf(i));
			entryList.add(user);
		}
		
		ArrayList<UserDto> expectReturn = new ArrayList<UserDto>();
		for (int i = 0; i < 10; i++) {
			UserDto userDto = new UserDto("user"+i, "000000");
			expectReturn.add(userDto);
		}
		
		when(userRepository.findAll()).thenReturn(entryList);
		
		when(toUserDto.convertToDTO(any(User.class))).thenCallRealMethod();
		
		List<UserDto> realResultList = userService.getAll();
		
		assertEquals(expectReturn, realResultList);
		assertEquals(expectReturn.size(), realResultList.size());
	}
	
	

}
