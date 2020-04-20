package br.com.compasso.pautas.exception.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.compasso.pautas.exception.InvalidPollSessionException;
import br.com.compasso.pautas.exception.UserNotPermitedException;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({IllegalArgumentException.class,UserNotPermitedException.class, 
		              InvalidPollSessionException.class, IllegalStateException.class,
		              NoSuchElementException.class, EntityNotFoundException.class})
	public ErroDeFormularioDto handle(RuntimeException exception) {

		return new ErroDeFormularioDto(exception.getMessage());
		
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public List<FieldErrorsExceptionHandler> handleFieldErrors( MethodArgumentNotValidException exception ) {
		
		List<FieldErrorsExceptionHandler> errorDtoList = new ArrayList<FieldErrorsExceptionHandler>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		fieldErrors.forEach(e -> {
			FieldErrorsExceptionHandler errorsExceptionHandler = new FieldErrorsExceptionHandler(
						e.getField(),
						messageSource.getMessage(e,LocaleContextHolder.getLocale())
						);
				errorDtoList.add(errorsExceptionHandler);
		});
		
		return errorDtoList;
	}

	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ErroDeFormularioDto handle(HttpMessageNotReadableException exception) {
		return new ErroDeFormularioDto("Json parsing error, all fields must be present and contain valid values");
	}


}
