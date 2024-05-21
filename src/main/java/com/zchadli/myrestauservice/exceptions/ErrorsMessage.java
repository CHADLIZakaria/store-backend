package com.zchadli.myrestauservice.exceptions;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorsMessage {
    AUTHENTIFICATION_FAILED("Username or password is incorrect", HttpStatus.UNAUTHORIZED),
    USERNAME_NOT_FOUND("Username not found", HttpStatus.NOT_FOUND),
    CATEGORY_NOT_FOUND("Category introuvable", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND("Produit introuvable", HttpStatus.NOT_FOUND),
    ROLE_NOT_FOUND("Role introuvable", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus httpStatus;

    private ErrorsMessage(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    
 
}
