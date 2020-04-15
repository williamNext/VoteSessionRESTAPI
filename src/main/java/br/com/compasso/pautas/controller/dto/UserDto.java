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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((AssociateRegistrationNumber == null) ? 0 : AssociateRegistrationNumber.hashCode());
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
		if (AssociateRegistrationNumber == null) {
			if (other.AssociateRegistrationNumber != null)
				return false;
		} else if (!AssociateRegistrationNumber.equals(other.AssociateRegistrationNumber))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
