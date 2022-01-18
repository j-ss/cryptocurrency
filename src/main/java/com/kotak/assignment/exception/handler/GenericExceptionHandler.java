package com.kotak.assignment.exception.handler;

import com.kotak.assignment.controller.model.response.GenericResponse;
import com.kotak.assignment.exception.EntityNotFoundException;
import com.kotak.assignment.exception.GenericException;
import com.kotak.assignment.util.Utility;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletRequest;
import java.util.Locale;

import static com.kotak.assignment.util.Utility.buildGenericResponse;

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<GenericResponse<Object>> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request){
        String path  = ((ServletWebRequest) request).getRequest().getRequestURI();
        return new ResponseEntity<>(buildGenericResponse(null,ex.getMessage(), ExceptionUtils.getStackTrace(ex),path),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<GenericResponse<Object>> handleGenericException(GenericException ex,WebRequest request ){
        String path  = ((ServletWebRequest) request).getRequest().getRequestURI();
        return new ResponseEntity<>(buildGenericResponse(null,ex.getMessage(), ExceptionUtils.getStackTrace(ex),path),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse<Object>> handleException(Exception ex,WebRequest request){
        String path  = ((ServletWebRequest) request).getRequest().getRequestURI();
        return new ResponseEntity<>(buildGenericResponse(null,ex.getMessage(), ExceptionUtils.getStackTrace(ex),path),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
