package br.com.compasso.pautas.converter;

import org.springframework.stereotype.Component;

import br.com.compasso.pautas.controller.dto.UserDto;
import br.com.compasso.pautas.converter.builder.UserDtoBuilder;
import br.com.compasso.pautas.model.User;

@Component
public class UserToUserDto implements EntityToDtoConverter<User, UserDto> {

	@Override
	public UserDto convertToDTO(User user) {
		
		UserDto userDto = new UserDtoBuilder()
				.setName(user.getName())
				.setAssociateRegistrationNumber(user.getAssociateRegistrationNumber())
				.build();
		return userDto;
	}

}
