package br.com.compasso.pautas.controller.dto;

public class UserDto {
	
	private String name;
	private String associateRegistrationNumber;
	
	
	
	public UserDto(String name, String associateRegistrationNumber) {
		this.name = name;
		this.associateRegistrationNumber = associateRegistrationNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAssociateRegistrationNumber() {
		return associateRegistrationNumber;
	}
	public void setAssociateRegistrationNumber(String associateRegistrationNumber) {
		this.associateRegistrationNumber = associateRegistrationNumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associateRegistrationNumber == null) ? 0 : associateRegistrationNumber.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		if (associateRegistrationNumber == null) {
			if (other.associateRegistrationNumber != null)
				return false;
		} else if (!associateRegistrationNumber.equals(other.associateRegistrationNumber))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
