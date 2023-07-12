package com.student.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.student.config.ResponseStructure;

@RestControllerAdvice
public class ApplicationHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> studentNotFoundById(StudentNotFoundByIdException exception) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(exception.getMessage());
		responseStructure.setData("Student Not Found By given Id!!");
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		//Here we are receiving all the field error as ObjectError type
		//as there can be multiple field errors so we are adding it inside a list
		//using ex.getAllErrors() present inside MethodArgumentNotValidException class.
		List<ObjectError> errorList=ex.getAllErrors();
		
		//Creating map because we need to print error as key and value pair
		//(field : message)
		Map<String, String> errorMap=new HashMap<String, String>();
		
		//A foreach loop to iterate through errorList
		for (ObjectError error : errorList) {
			
			//Downcasting it to FieldError class type class in order to fetch name using
			//fieldError.getField()
			FieldError fieldError=(FieldError) error;
			String field=fieldError.getField();
			
			//Getting the default error  message which is set by us in the validation.
			//Using the getDefaultMessage() present in ObjectError class.
			String message=error.getDefaultMessage();
			
			//Adding the field and message inside the map as key and value pair.
			errorMap.put(field, message);
		}
		return new ResponseEntity<Object>(errorMap,HttpStatus.BAD_REQUEST);
	}

}
