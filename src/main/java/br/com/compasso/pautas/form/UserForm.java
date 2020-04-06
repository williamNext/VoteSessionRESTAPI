package br.com.compasso.pautas.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.compasso.pautas.model.User;

public class UserForm {
	@NotBlank @Length(min = 3)
	private String name;
	@NotBlank  @Length(min = 4)
	private String associateRegistrationNumber;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAssociateRegistrationNumber() {
		return associateRegistrationNumber;
	}

	public void setAssociateRegistrationNumber(String AssociateRegistrationNumber) {
		this.associateRegistrationNumber = AssociateRegistrationNumber;
	} 
	
	public User convert() {
		return new User(name,associateRegistrationNumber);
	}
}
