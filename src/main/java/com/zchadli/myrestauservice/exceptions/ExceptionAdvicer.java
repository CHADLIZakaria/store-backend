package com.zchadli.myrestauservice.exceptions;

import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvicer {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiExceptionError> handleNotFoundException(BusinessException exception, HttpServletRequest request) {
        ApiExceptionError error = 
            new ApiExceptionError(exception.getErrorsMessage().getHttpStatus().value(), exception.getErrorsMessage().getMessage(), request.getServletPath()); 
        log.error(Level.SEVERE+ "no such element exception "+exception.getMessage(), exception);
        return ResponseEntity.status(exception.getErrorsMessage().getHttpStatus()).body(error);
    }

    
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiExceptionError> handleNotFoundException2(BusinessException exception, HttpServletRequest request) {
        ApiExceptionError error = 
            new ApiExceptionError(exception.getErrorsMessage().getHttpStatus().value(), exception.getErrorsMessage().getMessage(), request.getServletPath()); 
        log.error(Level.SEVERE+ "no such element exception "+exception.getMessage(), exception);
        return ResponseEntity.status(exception.getErrorsMessage().getHttpStatus()).body(error);
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiExceptionError> handleAuthException(BusinessException exception, HttpServletRequest request) {
        ApiExceptionError error = 
            new ApiExceptionError(exception.getErrorsMessage().getHttpStatus().value(), exception.getErrorsMessage().getMessage(), request.getServletPath()); 
        log.error(Level.SEVERE+ "no such element exception "+exception.getMessage(), exception);
        return ResponseEntity.status(exception.getErrorsMessage().getHttpStatus()).body(error);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiExceptionError> handleAuth1Exception(BusinessException exception, HttpServletRequest request) {
        ApiExceptionError error = 
            new ApiExceptionError(exception.getErrorsMessage().getHttpStatus().value(), exception.getErrorsMessage().getMessage(), request.getServletPath()); 
        log.error(Level.SEVERE+ "no such element exception "+exception.getMessage(), exception);
        return ResponseEntity.status(exception.getErrorsMessage().getHttpStatus()).body(error);
    }

    
   

}
