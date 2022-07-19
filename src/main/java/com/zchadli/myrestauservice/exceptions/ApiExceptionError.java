package com.zchadli.myrestauservice.exceptions;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiExceptionError {
    private int status;
    private String message;
    private Long timestamp;
    private String path;

    public ApiExceptionError(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = new Date().getTime();
    }

}
