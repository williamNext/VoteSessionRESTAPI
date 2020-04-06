package br.com.compasso.pautas.controller.dto;

public class UserDto {
	
	private String name;
	private String AssociateRegistrationNumber;
	
	
	
	public UserDto(String name, String associateRegistrationNumber) {
		this.name = name;
		AssociateRegistrationNumber = associateRegistrationNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAssociateRegistrationNumber() {
		return AssociateRegistrationNumber;
	}
	public void setAssociateRegistrationNumber(String associateRegistrationNumber) {
		AssociateRegistrationNumber = associateRegistrationNumber;
	}
	
	
}
