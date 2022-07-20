package com.vanchondo.tfm.controllers;

import com.vanchondo.tfm.dtos.ErrorDTO;
import com.vanchondo.tfm.exceptions.ConflictException;
import com.vanchondo.tfm.exceptions.NotFoundException;
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
        logger.error(ex);
        List<String> errorMessages = ex.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        return buildResponseError(errorMessages, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorDTO> exception(ConflictException ex){
        logger.error(ex);
        List<String> errorMessages = Collections.singletonList(ex.getLocalizedMessage());

        return buildResponseError(errorMessages, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> exception(NotFoundException ex){
        logger.error(ex);
        List<String> errorMessages = Collections.singletonList(ex.getLocalizedMessage());

        return buildResponseError(errorMessages, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> exception(Exception ex){
        logger.error(ex);
        List<String> errorMessages = Collections.singletonList("Internal server error");

        return buildResponseError(errorMessages, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorDTO> buildResponseError(List<String> errorMessages, HttpStatus status){
        ErrorDTO error = new ErrorDTO(status.toString(), status.value(), errorMessages);

        return ResponseEntity.status(status.value()).body(error);
    }


}
