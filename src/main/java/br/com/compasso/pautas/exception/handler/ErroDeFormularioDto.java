package br.com.compasso.pautas.exception.handler;

public class ErroDeFormularioDto {
	
	private String error;
	
	public ErroDeFormularioDto(String error) {
		this.error = error;
	}
	public String getError() {
		return error;
	}
	
	

}
