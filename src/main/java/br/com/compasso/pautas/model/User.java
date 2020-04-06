package br.com.compasso.pautas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String name;
	private String AssociateRegistrationNumber;

	
	public User() {}
	
	public User(String name, String associateRegistrationNumber) {
		this.name = name;
		this.AssociateRegistrationNumber = associateRegistrationNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public void setAssociateRegistrationNumber(String AssociateRegistrationNumber) {
		this.AssociateRegistrationNumber = AssociateRegistrationNumber;
	}

}
