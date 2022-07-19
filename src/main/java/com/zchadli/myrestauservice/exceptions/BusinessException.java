package com.zchadli.myrestauservice.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class BusinessException  extends RuntimeException {
    private ErrorsMessage errorsMessage;
}
