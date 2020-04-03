package br.com.compasso.pautas.converter;

public interface EntityToDtoConverter <entity,converted> {

	converted convertToDTO(entity entitytoConvert);
	
}
