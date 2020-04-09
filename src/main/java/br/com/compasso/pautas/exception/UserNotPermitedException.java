package br.com.compasso.pautas.exception;

public class UserNotPermitedException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public UserNotPermitedException(String message) {
		super(message);
	}
}
