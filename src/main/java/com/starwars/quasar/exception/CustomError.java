package com.starwars.quasar.exception;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Builder
@ToString
public class CustomError {
	private HttpStatus status;
	private String message;
	
	public CustomError(HttpStatus status, String message) {
	    super();
	    this.status = status;
	    this.message = message;
	}
}
