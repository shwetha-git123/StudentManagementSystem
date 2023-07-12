package com.student.exception;

public class StudentNotFoundByIdException extends RuntimeException {
private String message;

public String getMessage() {
	return message;
}

public StudentNotFoundByIdException(String message) {
	super();
	this.message = message;
}

}
