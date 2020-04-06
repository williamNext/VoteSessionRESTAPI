package br.com.compasso.pautas.converter.builder;

import br.com.compasso.pautas.controller.dto.UserDto;

public class UserDtoBuilder {
	private String name;
	private String associateRegistrationNumber;
	
	
	
	public UserDtoBuilder setName(String name) {
		this.name = name;
		return this;
	}
	public UserDtoBuilder setAssociateRegistrationNumber(String associateRegistrationNumber) {
		this.associateRegistrationNumber=associateRegistrationNumber;
		return this;
	}
	
	public UserDto build() {
		return new UserDto(name,associateRegistrationNumber);
	}
}
