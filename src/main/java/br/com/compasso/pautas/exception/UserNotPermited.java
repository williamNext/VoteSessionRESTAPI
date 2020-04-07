package br.com.compasso.pautas.exception;

public class UserNotPermited extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public UserNotPermited(String message) {
		super(message);
	}
}
