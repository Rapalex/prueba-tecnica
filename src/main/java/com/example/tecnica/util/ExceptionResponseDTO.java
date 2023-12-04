package com.example.tecnica.util;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExceptionResponseDTO {
	
	@JsonProperty("errorCode")
	private String errorCode;

	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	public ExceptionResponseDTO(String errorCode, String errorMessage, LocalDateTime timestamp) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.timestamp = timestamp;
	}
}
