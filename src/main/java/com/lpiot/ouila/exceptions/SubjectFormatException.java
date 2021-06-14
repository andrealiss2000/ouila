package com.lpiot.ouila.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class SubjectFormatException extends RuntimeException {

	public SubjectFormatException(String message) {
		super(message);

	}

}
