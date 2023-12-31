package com.AugustoSouza.SistemaDeTransferencia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.AugustoSouza.SistemaDeTransferencia.DTO.ErrorDTO;
import com.AugustoSouza.SistemaDeTransferencia.Exceptions.UserAlreadyExistsException;

@ControllerAdvice
public class GlobalExceptionControllerHandler {
    
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> UserAlreadyExistsException(UserAlreadyExistsException e){
        ErrorDTO error = new ErrorDTO(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
