package com.zchadli.myrestauservice.exceptions;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorsMessage {
    AUTHENTIFICATION_FAILED("Nom d’utilisateur ou mot de passe est incorrect", HttpStatus.UNAUTHORIZED),
    USERNAME_NOT_FOUND("Nom d’utilisateur introuvable", HttpStatus.NOT_FOUND),
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
