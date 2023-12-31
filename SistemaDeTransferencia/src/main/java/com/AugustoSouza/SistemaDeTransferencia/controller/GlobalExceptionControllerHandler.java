package com.AugustoSouza.SistemaDeTransferencia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.AugustoSouza.SistemaDeTransferencia.DTO.ErrorDTO;
import com.AugustoSouza.SistemaDeTransferencia.Exceptions.TokenNotFoundException;

// @ControllerAdvice
// public class GlobalExceptionControllerHandler {
    
//     @ExceptionHandler(TokenNotFoundException.class)
//     public ResponseEntity<ErrorDTO> handleTokenNotFoundException(TokenNotFoundException e){
//         ErrorDTO error = new ErrorDTO(e.getMessage());
//         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
//     }
// }
