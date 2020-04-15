package br.com.compasso.pautas.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;

import br.com.compasso.pautas.controller.dto.UserDto;
import br.com.compasso.pautas.converter.UserToUserDto;
import br.com.compasso.pautas.form.UserForm;
import br.com.compasso.pautas.model.User;
import br.com.compasso.pautas.service.UserService;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(UserController.class)

class UserControllerTesT {

	@MockBean
	private UserService userService;

	@MockBean
	private UserToUserDto toUserDto;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test() throws Exception {
		UserForm userForm = new UserForm();
		userForm.setAssociateRegistrationNumber("1234");
		userForm.setName("William");

		UserDto userDto = new UserDto("William", "1234");

		Gson gson = new Gson();

		doNothing().when(userService).save(any(User.class));
		when(toUserDto.convertToDTO(any(User.class))).thenCallRealMethod();

		mockMvc.perform(MockMvcRequestBuilders
				.post("/associate")
				.content(gson.toJson(userForm))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().string(gson.toJson(userDto)))
				.andExpect(MockMvcResultMatchers.status().isCreated());

	}

}
