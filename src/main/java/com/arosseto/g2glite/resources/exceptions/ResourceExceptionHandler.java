package com.arosseto.g2glite.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.arosseto.g2glite.services.exceptions.AuthorizationException;
import com.arosseto.g2glite.services.exceptions.DatabaseException;
import com.arosseto.g2glite.services.exceptions.FileException;
import com.arosseto.g2glite.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError stError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(stError);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> databaseError(DatabaseException e, HttpServletRequest request) {
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError stError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(stError);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		String error = "Invalid Argument";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ValidationError stError = new ValidationError(Instant.now(), status.value(), error, "Validation Error", request.getRequestURI());
		
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			stError.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(stError);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request) {
		String error = "Authorization error";
		StandardError stError = new StandardError(Instant.now(), HttpStatus.FORBIDDEN.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(stError);
	}
	
	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request) {
		String error = "File Handling Error";
		StandardError stError = new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stError);
	}
	
	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request) {
		String error = "Amazon Service Error";
		HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
		StandardError stError = new StandardError(Instant.now(), code.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(code).body(stError);
	}
	
	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request) {
		String error = "Amazon Client Errorr";
		StandardError stError = new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stError);
	}
	
	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request) {
		String error = "Amazon S3 Errorr";
		StandardError stError = new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stError);
	}
}
