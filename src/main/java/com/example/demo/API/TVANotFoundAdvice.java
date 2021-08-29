/*
 * Copyright (c) Code écrit par Bedeschi Louis.
 */

package com.example.demo.API;

import Exceptions.TVANotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class TVANotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(TVANotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(TVANotFoundException ex) {
        return ex.getMessage();
    }
}
