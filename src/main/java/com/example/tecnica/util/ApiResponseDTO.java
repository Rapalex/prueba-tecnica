package com.example.tecnica.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponseDTO {

	@JsonProperty("responseMessage")
	private String responseMessage;

	@JsonProperty("responseCode")
	private String responseCode;

	public String getResponseMessage() {
		return responseMessage;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public ApiResponseDTO(String responseMessage, String responseCode) {
		this.responseMessage = responseMessage;
		this.responseCode = responseCode;
	}

}
