package com.vanchondo.tfm.controllers;

import com.vanchondo.tfm.dtos.ErrorDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerException {
    private Logger logger = LogManager.getLogger();

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> exception(MethodArgumentNotValidException ex){
        List<String> errorMessages = ex.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST.value(), errorMessages);

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> exception(Exception ex){
        logger.error(ex);
        List<String> errorMessages = Collections.singletonList("Internal server error");
        ErrorDTO error = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessages);

        return ResponseEntity.internalServerError().body(error);
    }


}
