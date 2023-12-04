package com.example.tecnica.util;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Clase encargada de manejar las excepciones identificadas del aplicativo
 */
@ControllerAdvice
public class ExceptionResponseHandler {

	/**
	 * Metodo para manejar el código de error 204 cuando no se encuentre el film en
	 * el api de starwars
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = { NotFound.class })
	public ResponseEntity<Object> validationException(Exception ex, WebRequest request) {
		ex.printStackTrace();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Metodo para manejar el código de error 400 cuando se envíe un id de film
	 * incorrecto
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = { HandlerMethodValidationException.class, MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleValidationErrors(Exception ex) {
		return new ResponseEntity<>(new ExceptionResponseDTO("ERR_001", "Error en la solicitud", LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * Metodo para manejar el código de error 400 cuando se envíe un dato incorrecto
	 * en el body de el api de edición de films
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = { MethodArgumentNotValidException.class, HttpMessageNotReadableException.class })
	public ResponseEntity<Object> handleValidationErrors2(Exception ex) {
		return new ResponseEntity<>(new ExceptionResponseDTO("ERR_002",
				"Los datos ingresados no son correctos, verifique la información y vuelva a intentar",
				LocalDateTime.now()), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Metodo para manejar el código de error 400 cuando se trate de editar o
	 * eliminar un film que no esté guardado en la base de datos
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = { MissingPathVariableException.class })
	public ResponseEntity<Object> handleNotFoundInDatabase(MissingPathVariableException ex) {
		return new ResponseEntity<>(new ExceptionResponseDTO("ERR_003",
				"El film con el que está tratando de realizar la operación no se encuentra registrado",
				LocalDateTime.now()), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Metodo para manejar el código de error 500 cuando se presente un error en el
	 * aplicativo y no esté contemplado en los errores específicos manejados
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleException(Exception ex) {
		ex.printStackTrace();
		return new ResponseEntity<>(
				new ExceptionResponseDTO("ERR_004",
						"Estamos presentando fallas, por favor intentar de nuevo más tarde.", LocalDateTime.now()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
