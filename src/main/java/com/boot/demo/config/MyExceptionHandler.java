package com.boot.demo.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//import com.prodemy.boot.demo.exception.BookNotFoundException;
//import com.prodemy.boot.demo.exception.PublisherAlreadyExistsException;
//import com.prodemy.boot.demo.exception.PublisherNotFoundException;
import com.boot.demo.exception.UserAlreadyExistsException;
import com.boot.demo.model.response.HttpResponseModel;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({ BadCredentialsException.class })
    @ResponseBody
	public ResponseEntity<HttpResponseModel> handleBadCredentialException(Exception ex) {
		
		HttpResponseModel<Object> model = new HttpResponseModel<Object>();
		model.setStatus(-999);
		model.setMessage(ex.getMessage());

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(model);
	}

	@ExceptionHandler({ InsufficientAuthenticationException.class })
    @ResponseBody
	public ResponseEntity<HttpResponseModel> handleInsufficientAuthenticationException(Exception ex) {
		
		HttpResponseModel<Object> model = new HttpResponseModel<Object>();
		model.setStatus(-999); 
		model.setMessage(ex.getMessage()); 
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(model);
	}
	
	@ExceptionHandler({ UserAlreadyExistsException.class })
    @ResponseBody
	public ResponseEntity<HttpResponseModel> userAlreadyExistsException(Exception ex) {
		
		HttpResponseModel<Object> model = new HttpResponseModel<Object>();
		model.setStatus(4001); 
		model.setMessage(ex.getMessage()); 

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
	}
}

